package com.example.springboot.presentation.purchase;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.exception.ItemNotFoundException;
import com.example.domain.model.user.UserIdentifier;
import com.example.springboot.application.service.PurchaseRegistrationService;
import com.example.springboot.application.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
  ItemService itemService;
  PurchaseRegistrationService purchaseRegistrationService;

  HttpSession httpSession;

  Logger log = LoggerFactory.getLogger(PurchaseController.class);

  public PurchaseController(
      ItemService itemService,
      PurchaseRegistrationService purchaseRegistrationService,
      HttpSession httpSession) {
    this.itemService = itemService;
    this.purchaseRegistrationService = purchaseRegistrationService;
    this.httpSession = httpSession;
  }

  @GetMapping
  public String get(
      @ModelAttribute PurchaseGetRequest request, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("purchasePostRequest", new PurchasePostRequest());
      model.addAttribute("errorMessage", "商品の指定がありません");
      return "purchase";
    }
    Item item = itemService.get(request.toItemName());
    model.addAttribute("itemName", request.getItemName());
    model.addAttribute("price", request.getPrice());
    model.addAttribute("purchasePostRequest", new PurchasePostRequest());
    httpSession.setAttribute("itemIdentifier", item.itemIdentifier().value());
    return "purchase";
  }

  @PostMapping
  public String post(
      @Valid @ModelAttribute PurchasePostRequest request, Model model, Principal principal) {
    if (httpSession.getAttribute("itemIdentifier") instanceof Integer itemIdentifierValue) {
      UserIdentifier userIdentifier = new UserIdentifier(principal.getName());
      ItemIdentifier itemIdentifier = new ItemIdentifier(itemIdentifierValue);
      purchaseRegistrationService.register(
          userIdentifier, itemIdentifier, request.toFullName(), request.toAddress());
      httpSession.removeAttribute("itemIdentifier");
      return "redirect:/item";
    }
    model.addAttribute("purchasePostRequest", new PurchasePostRequest());
    model.addAttribute("errorMessage", "商品の指定がありません");
    return "purchase";
  }

  @ExceptionHandler({ItemNotFoundException.class})
  public String handleException(ItemNotFoundException e, Model model) {
    log.warn(e.getMessage());
    model.addAttribute("purchasePostRequest", new PurchasePostRequest());
    model.addAttribute("errorMessage", "商品が見つかりません");
    return "purchase";
  }
}

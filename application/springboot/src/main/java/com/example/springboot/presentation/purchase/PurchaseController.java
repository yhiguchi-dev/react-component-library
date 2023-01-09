package com.example.springboot.presentation.purchase;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.user.UserIdentifier;
import com.example.springboot.application.service.PurchaseRegistrationService;
import com.example.springboot.application.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.security.Principal;
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
  public String post(@Valid @ModelAttribute PurchasePostRequest request, Principal principal) {
    if (httpSession.getAttribute("itemIdentifier") instanceof Integer itemIdentifierValue) {
      UserIdentifier userIdentifier = new UserIdentifier(principal.getName());
      ItemIdentifier itemIdentifier = new ItemIdentifier(itemIdentifierValue);
      purchaseRegistrationService.register(
          userIdentifier, itemIdentifier, request.toFullName(), request.toAddress());
      httpSession.removeAttribute("itemIdentifier");
      return "redirect:/item";
    }
    return "purchase";
  }
}

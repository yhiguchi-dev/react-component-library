package com.example.springboot.presentation.item;

import com.example.query.*;
import com.example.springboot.application.service.ItemSummaryService;
import com.example.springboot.presentation.JsonTransformable;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController implements JsonTransformable<ItemsResponse> {

  ItemSummaryService itemSummaryService;

  public ItemController(ItemSummaryService itemSummaryService) {
    this.itemSummaryService = itemSummaryService;
  }

  @GetMapping
  public String get(
      @Valid @ModelAttribute ItemRequest itemRequest, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("itemRequest", new ItemRequest());
      return "item";
    }
    ItemCriteria itemCriteria = itemRequest.toItemCriteria();
    ItemSummary itemSummary = itemSummaryService.find(itemCriteria);
    ItemsResponse itemsResponse = new ItemsResponse(itemSummary, itemCriteria);
    model.addAttribute("resultJson", transform(itemsResponse));
    return "item";
  }
}

package com.example.springboot.presentation.history;

import com.example.domain.model.purchase.Purchases;
import com.example.domain.model.user.UserIdentifier;
import com.example.springboot.application.service.purchase.PurchaseService;
import com.example.springboot.presentation.JsonTransformable;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController implements JsonTransformable<HistoriesResponse> {

  PurchaseService purchaseService;

  public HistoryController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @GetMapping
  public String get(Model model, Principal principal) {
    UserIdentifier userIdentifier = new UserIdentifier(principal.getName());
    Purchases purchases = purchaseService.find(userIdentifier);
    HistoriesResponse historiesResponse = new HistoriesResponse(purchases);
    model.addAttribute("resultJson", transform(historiesResponse));
    return "history";
  }
}

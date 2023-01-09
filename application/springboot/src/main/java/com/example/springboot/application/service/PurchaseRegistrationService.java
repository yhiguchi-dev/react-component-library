package com.example.springboot.application.service;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.purchase.Address;
import com.example.domain.model.purchase.FullName;
import com.example.domain.model.purchase.Purchase;
import com.example.domain.model.purchase.PurchaseCreator;
import com.example.domain.model.user.UserIdentifier;
import com.example.springboot.application.service.item.ItemService;
import com.example.springboot.application.service.purchase.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseRegistrationService {

  PurchaseService purchaseService;
  ItemService itemService;

  public PurchaseRegistrationService(PurchaseService purchaseService, ItemService itemService) {
    this.purchaseService = purchaseService;
    this.itemService = itemService;
  }

  public void register(
      UserIdentifier userIdentifier,
      ItemIdentifier itemIdentifier,
      FullName fullName,
      Address address) {
    Item item = itemService.get(itemIdentifier);
    Purchase purchase = PurchaseCreator.create(userIdentifier, item, fullName, address);
    purchaseService.register(purchase);
  }
}

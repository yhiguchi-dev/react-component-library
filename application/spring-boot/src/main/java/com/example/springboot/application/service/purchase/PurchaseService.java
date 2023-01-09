package com.example.springboot.application.service.purchase;

import com.example.domain.model.purchase.Purchase;
import com.example.domain.model.purchase.PurchaseRepository;
import com.example.domain.model.purchase.Purchases;
import com.example.domain.model.user.UserIdentifier;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
  PurchaseRepository purchaseRepository;

  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public void register(Purchase purchase) {
    purchaseRepository.register(purchase);
  }

  public Purchases find(UserIdentifier userIdentifier) {
    return purchaseRepository.find(userIdentifier);
  }
}

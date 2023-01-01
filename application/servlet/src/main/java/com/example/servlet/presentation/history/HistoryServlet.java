package com.example.servlet.presentation.history;

import com.example.domain.model.purchase.PurchaseRepository;
import com.example.domain.model.purchase.Purchases;
import com.example.domain.model.user.UserIdentifier;
import com.example.query.*;
import com.example.servlet.application.service.purchase.PurchaseService;
import com.example.servlet.infrastructure.datasource.DatabaseAccessor;
import com.example.servlet.infrastructure.datasource.PurchaseDataSource;
import com.example.servlet.presentation.JsonTransformable;
import com.example.servlet.presentation.ViewForwardable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "historyServlet", value = "/history")
public class HistoryServlet extends HttpServlet
    implements ViewForwardable, JsonTransformable<HistoriesResponse> {

  PurchaseService purchaseService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    PurchaseRepository purchaseRepository = new PurchaseDataSource(new DatabaseAccessor());
    this.purchaseService = new PurchaseService(purchaseRepository);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("userIdentifier") instanceof String userIdentifierValue) {
      Purchases purchases = purchaseService.find(new UserIdentifier(userIdentifierValue));
      HistoriesResponse historiesResponse = new HistoriesResponse(purchases);
      request.setAttribute("resultJson", transform(historiesResponse));
      forward("history", request, response);
    }
  }
}

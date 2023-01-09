package com.example.servlet.presentation.purchase;

import com.example.domain.model.item.Item;
import com.example.domain.model.item.ItemIdentifier;
import com.example.domain.model.item.ItemName;
import com.example.domain.model.item.ItemRepository;
import com.example.domain.model.purchase.*;
import com.example.domain.model.user.UserIdentifier;
import com.example.domain.model.user.exception.PasswordUnMatchException;
import com.example.domain.model.user.exception.UserNotFoundException;
import com.example.servlet.application.service.PurchaseRegistrationService;
import com.example.servlet.application.service.item.ItemService;
import com.example.servlet.application.service.purchase.PurchaseService;
import com.example.servlet.infrastructure.datasource.DatabaseAccessor;
import com.example.servlet.infrastructure.datasource.ItemDataSource;
import com.example.servlet.infrastructure.datasource.PurchaseDataSource;
import com.example.servlet.presentation.ViewForwardable;
import com.example.servlet.presentation.ViewRedirectable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "purchaseServlet", value = "/purchase")
public class PurchaseServlet extends HttpServlet implements ViewForwardable, ViewRedirectable {

  ItemService itemService;
  PurchaseRegistrationService purchaseRegistrationService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    DatabaseAccessor databaseAccessor = new DatabaseAccessor();
    ItemRepository itemRepository = new ItemDataSource(databaseAccessor);
    this.itemService = new ItemService(itemRepository);
    PurchaseRepository purchaseRepository = new PurchaseDataSource(databaseAccessor);
    PurchaseService purchaseService = new PurchaseService(purchaseRepository);
    this.purchaseRegistrationService =
        new PurchaseRegistrationService(purchaseService, itemService);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String itemName = request.getParameter("item_name");
    String price = request.getParameter("price");
    System.out.println(itemName);
    System.out.println(price);
    if (Objects.isNull(itemName) || Objects.isNull(price)) {
      forward("purchase", request, response);
      return;
    }
    request.setAttribute("item_name", itemName);
    request.setAttribute("price", price);
    Item item = itemService.get(new ItemName(itemName));
    HttpSession session = request.getSession();
    session.setAttribute("itemIdentifier", item.itemIdentifier().value());
    forward("purchase", request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      String lastName = request.getParameter("last_name");
      String firstName = request.getParameter("first_name");
      String zipCode = request.getParameter("zip_code");
      String address = request.getParameter("address");
      String address2 = request.getParameter("address2");
      String address3 = request.getParameter("address3");

      HttpSession session = request.getSession();
      if (session.getAttribute("userIdentifier") instanceof String userIdentifierValue
          && session.getAttribute("itemIdentifier") instanceof Integer itemIdentifierValue) {
        UserIdentifier userIdentifier = new UserIdentifier(userIdentifierValue);
        ItemIdentifier itemIdentifier = new ItemIdentifier(itemIdentifierValue);
        FullName fullName = new FullName(new FirstName(firstName), new LastName(lastName));
        Address addressValue = new Address(zipCode, address, address2, address3);
        purchaseRegistrationService.register(
            userIdentifier, itemIdentifier, fullName, addressValue);
        session.removeAttribute("itemIdentifier");
      }
      redirect("item", request, response);
    } catch (UserNotFoundException | PasswordUnMatchException e) {
      request.setAttribute("errorMessage", "購入に失敗しました");
      forward("purchase", request, response);
    }
  }
}

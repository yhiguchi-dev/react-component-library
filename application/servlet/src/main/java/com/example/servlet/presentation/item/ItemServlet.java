package com.example.servlet.presentation.item;

import com.example.query.*;
import com.example.servlet.application.service.ItemSummaryService;
import com.example.servlet.infrastructure.datasource.DatabaseAccessor;
import com.example.servlet.infrastructure.datasource.ItemSummaryDataSource;
import com.example.servlet.presentation.JsonTransformable;
import com.example.servlet.presentation.ViewForwardable;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "itemServlet", value = "/item")
public class ItemServlet extends HttpServlet
    implements ViewForwardable, JsonTransformable<ItemsResponse> {

  ItemSummaryService itemSummaryService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ItemSummaryRepository itemSummaryRepository = new ItemSummaryDataSource(new DatabaseAccessor());
    this.itemSummaryService = new ItemSummaryService(itemSummaryRepository);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String page = request.getParameter("page");
    String itemName = request.getParameter("item_name");
    if (Objects.isNull(page) && Objects.isNull(itemName)) {
      forward("item", request, response);
      return;
    }
    Integer pageValue = Objects.nonNull(page) ? Integer.parseInt(page) : 1;
    ItemCriteria itemCriteria =
        new ItemCriteria(itemName, new Pagination(new Page(pageValue), new PerPage(20)));
    ItemSummary itemSummary = itemSummaryService.find(itemCriteria);
    ItemsResponse itemsResponse = new ItemsResponse(itemSummary, itemCriteria);
    request.setAttribute("resultJson", transform(itemsResponse));
    forward("item", request, response);
  }
}

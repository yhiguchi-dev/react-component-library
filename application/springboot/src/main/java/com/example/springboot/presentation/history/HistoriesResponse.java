package com.example.springboot.presentation.history;

import com.example.domain.model.purchase.Purchases;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.StreamSupport;

public class HistoriesResponse {

  @JsonProperty("histories")
  List<HistoryResponse> histories;

  public HistoriesResponse(Purchases purchases) {
    this.histories =
        StreamSupport.stream(purchases.spliterator(), false).map(HistoryResponse::new).toList();
  }
}

package com.example.servlet.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonTransformable<TYPE> {

  default String transform(TYPE type) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}

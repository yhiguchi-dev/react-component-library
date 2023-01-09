package com.example.springboot.presentation.config;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
  USER;

  @Override
  public String getAuthority() {
    return name();
  }
}

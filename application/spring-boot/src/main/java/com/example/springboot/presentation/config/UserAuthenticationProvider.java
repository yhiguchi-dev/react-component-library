package com.example.springboot.presentation.config;

import com.example.domain.model.authentication.UserAuthenticationException;
import com.example.domain.model.user.User;
import com.example.springboot.application.service.PasswordAuthenticationService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

  PasswordAuthenticationService passwordAuthenticationService;

  Logger log = LoggerFactory.getLogger(UserAuthenticationProvider.class);

  public UserAuthenticationProvider(PasswordAuthenticationService passwordAuthenticationService) {
    this.passwordAuthenticationService = passwordAuthenticationService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (authentication.getPrincipal() instanceof String username
        && authentication.getCredentials() instanceof String password) {
      try {
        User authenticatedUser = passwordAuthenticationService.authenticate(username, password);
        return UsernamePasswordAuthenticationToken.authenticated(
            authenticatedUser.userIdentifier().value(), password, List.of(Authority.USER));
      } catch (UserAuthenticationException e) {
        log.warn(e.getMessage());
        throw new BadCredentialsException("認証に失敗しました", e);
      }
    }
    log.warn("クレデンシャル情報の取得に失敗しました");
    throw new BadCredentialsException("クレデンシャル情報の取得に失敗しました");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}

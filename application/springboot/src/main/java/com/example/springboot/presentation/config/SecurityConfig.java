package com.example.springboot.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(
        httpSecurityFormLoginConfigurer ->
            httpSecurityFormLoginConfigurer.loginPage("/login").permitAll());
    http.logout(
        httpSecurityLogoutConfigurer -> {
          httpSecurityLogoutConfigurer.logoutSuccessUrl("/");
        });
    http.authorizeHttpRequests(
        authorizationManagerRequestMatcherRegistry -> {
          authorizationManagerRequestMatcherRegistry
              .requestMatchers("/login", "/registration")
              .permitAll();
        });
    return http.build();
  }
}

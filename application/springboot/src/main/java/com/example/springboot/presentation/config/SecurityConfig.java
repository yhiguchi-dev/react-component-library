package com.example.springboot.presentation.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  @Order(1)
  public SecurityFilterChain managementSecurityFilterChain(HttpSecurity http) throws Exception {
    http.securityMatcher(EndpointRequest.toAnyEndpoint())
        .authorizeHttpRequests()
        .anyRequest()
        .permitAll();
    return http.build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers(HttpMethod.GET, "/login", "/registration")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/login", "/registration")
        .permitAll()
        .anyRequest()
        .authenticated();
    http.formLogin()
        .loginPage("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .loginProcessingUrl("/login/processing")
        .successForwardUrl("/login/success")
        .failureUrl("/login/failure")
        .permitAll();
    http.logout()
        .logoutUrl("/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/login");
    return http.build();
  }
}

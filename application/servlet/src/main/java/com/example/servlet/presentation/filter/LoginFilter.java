package com.example.servlet.presentation.filter;

import com.example.servlet.presentation.ViewRedirectable;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(
    filterName = "loginFilter",
    urlPatterns = {"/*"})
public class LoginFilter implements Filter, ViewRedirectable {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (request instanceof HttpServletRequest httpServletRequest
        && response instanceof HttpServletResponse httpServletResponse) {
      String servletPath = httpServletRequest.getServletPath();
      if (ExcludedUrls.contains(servletPath)) {
        chain.doFilter(request, response);
        return;
      }
      HttpSession session = httpServletRequest.getSession(false);
      if (Objects.isNull(session) || Objects.isNull(session.getAttribute("userIdentifier"))) {
        redirect("login", httpServletRequest, httpServletResponse);
        return;
      }
    }
    chain.doFilter(request, response);
  }
}

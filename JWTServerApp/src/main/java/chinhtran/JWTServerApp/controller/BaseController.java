package chinhtran.JWTServerApp.controller;

import chinhtran.JWTServerApp.config.MyAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

  /**
   * Get AuthenticationToken from context.
   *
   * @return MyAuthenticationToken
   */
  public MyAuthenticationToken getAuthenticationToken() {
    return (MyAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
  }
}

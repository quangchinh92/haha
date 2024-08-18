package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostRes;

public interface AuthenticationService {
  /**
   * Authenticate user.
   *
   * @param req AuthenticationPostReq
   * @return AuthenticationPostRes
   */
  public AuthenticationPostRes authenticate(AuthenticationPostReq req);
}

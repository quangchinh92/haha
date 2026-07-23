package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.controller.user.model.PasswordForgetingReq;

public interface PasswordForgetingService {
  public void execute(PasswordForgetingReq req);

  public String generateCommonLangPassword();
}

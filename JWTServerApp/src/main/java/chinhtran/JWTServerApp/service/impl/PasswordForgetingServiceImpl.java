package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.controller.user.model.PasswordForgetingReq;
import chinhtran.JWTServerApp.service.PasswordForgetingService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordForgetingServiceImpl implements PasswordForgetingService {

  @Override
  public void execute(PasswordForgetingReq req) {
    // TODO Auto-generated method stub

  }

  @Override
  public String generateCommonLangPassword() {
    RandomStringUtils secure = RandomStringUtils.secure();
    StringBuffer password = new StringBuffer();
    // 5 upper case charactor.
    password.append(secure.next(5, 65, 90, true, true));
    // 5 lower case charactor.
    password.append(secure.next(5, 97, 122, true, true));

    // 5 random number
    password.append(secure.nextNumeric(5));

    // 5 specical charactor.
    password.append(secure.next(5, 33, 47, false, false));

    return password.toString();
  }
}

package chinhtran.JWTServerApp.converter;

import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.entity.UserEntity;

public class RegisterConverter {

  public static UserEntity convertRegisterPostReqToUserEntity(RegisterPostReq req) {
    UserEntity result = new UserEntity();
    result.setUsername(req.getUsername());
    result.setPassword(req.getPassword());
    result.setName(req.getName());
    result.setEmail(req.getEmail());
    result.setPhoneNumber(req.getPhoneNumber());
    return result;
  }
}

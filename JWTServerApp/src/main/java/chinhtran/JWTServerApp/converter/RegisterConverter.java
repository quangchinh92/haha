package chinhtran.JWTServerApp.converter;

import chinhtran.JWTServerApp.controller.user.model.UserPostReq;
import chinhtran.JWTServerApp.repository.entity.UserEntity;

public class RegisterConverter {

  public static UserEntity convertPostReqModelToUserEntity(UserPostReq req) {
    UserEntity result = new UserEntity();
    result.setUsername(req.getUsername());
    result.setPassword(req.getPassword());
    result.setName(req.getName());
    result.setEmail(req.getEmail());
    result.setPhoneNumber(req.getPhoneNumber());
    return result;
  }
}

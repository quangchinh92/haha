package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.controller.user.model.ChangePassReq;
import chinhtran.JWTServerApp.controller.user.model.UserGetRes;
import chinhtran.JWTServerApp.controller.user.model.UserPostReq;
import chinhtran.JWTServerApp.controller.user.model.UserPostRes;
import chinhtran.JWTServerApp.controller.user.model.UserPutReq;

public interface UserService {
  public UserPostRes create(UserPostReq model);

  public void update(Long id, UserPutReq model);

  public void changePass(Long id, ChangePassReq req);

  public UserGetRes getById(Long id);
}

package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.entity.UserEntity;

public interface RegisterService {
    public UserEntity regist(RegisterPostReq registerPostReq);
}

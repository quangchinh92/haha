package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostRes;

public interface AuthorizationService {

    public AuthorizationPostRes authrorize(AuthorizationPostReq req);
}

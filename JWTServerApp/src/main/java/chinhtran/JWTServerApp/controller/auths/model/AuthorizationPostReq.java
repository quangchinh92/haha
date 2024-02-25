package chinhtran.JWTServerApp.controller.auths.model;

import java.util.List;

import lombok.Data;

@Data
public class AuthorizationPostReq {
    private List<AuthorizationPostModel> authorizationList;
}

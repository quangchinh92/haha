package chinhtran.JWTServerApp.controller.auths.model;

import lombok.Data;

@Data
public class AuthenticationPostRes {
    private String username;
    private String jwt;
}

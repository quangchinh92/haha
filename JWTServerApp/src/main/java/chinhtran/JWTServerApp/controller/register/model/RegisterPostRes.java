package chinhtran.JWTServerApp.controller.register.model;

import lombok.Data;

@Data
public class RegisterPostRes {
    private String username;
    private String jwt;
}

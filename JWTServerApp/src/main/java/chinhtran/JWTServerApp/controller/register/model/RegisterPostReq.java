package chinhtran.JWTServerApp.controller.register.model;

import lombok.Data;

@Data
public class RegisterPostReq {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
}

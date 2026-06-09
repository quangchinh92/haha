package chinhtran.JWTServerApp.controller.user.model;

import lombok.Data;

@Data
public class UserPostReq {
  private String username;
  private String password;
  private String name;
  private String email;
  private String phoneNumber;
}

package chinhtran.JWTServerApp.controller.user.model;

import lombok.Data;

@Data
public class UserPutReq {
  private String name;
  private String email;
  private String phoneNumber;
}

package chinhtran.JWTServerApp.controller.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostRes {
  private String username;
  private String jwt;
}

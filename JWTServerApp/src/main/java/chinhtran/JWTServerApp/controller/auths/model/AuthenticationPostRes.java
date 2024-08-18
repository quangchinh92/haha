package chinhtran.JWTServerApp.controller.auths.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationPostRes {
  private String username;
  private String jwt;
}

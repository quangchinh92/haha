package chinhtran.JWTServerApp.controller.register.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterPostRes {
  private String username;
  private String jwt;
}

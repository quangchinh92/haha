package chinhtran.JWTServerApp.controller.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPostReq {
  @NotBlank private String username;
  @NotBlank private String password;
  @NotBlank private String name;
  @NotBlank private String email;
  @NotBlank private String phoneNumber;
}

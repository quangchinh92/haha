package chinhtran.JWTServerApp.controller.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class PasswordForgetingReq {
  @Getter @Setter @NotBlank @Email private String email;
}

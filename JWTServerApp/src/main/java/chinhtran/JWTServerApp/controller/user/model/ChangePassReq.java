package chinhtran.JWTServerApp.controller.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePassReq {
  @NotBlank
  @Size(min = 8, max = 20)
  private String oldPassword;

  @NotBlank
  @Size(min = 8, max = 20)
  private String newPassword;

  @NotBlank private String confirmNewPassword;
}

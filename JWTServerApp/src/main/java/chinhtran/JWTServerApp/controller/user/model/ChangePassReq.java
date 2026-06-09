package chinhtran.JWTServerApp.controller.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePassReq {
  private String oldPassword;
  private String newPassword;
  private String confirmNewPassword;
}

package chinhtran.JWTServerApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppAESKeyDto {

  private String id;

  private String password;

  private String salt;

  private String ivText;
}

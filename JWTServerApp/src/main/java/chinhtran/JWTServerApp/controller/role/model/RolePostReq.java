package chinhtran.JWTServerApp.controller.role.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RolePostReq {
  private Long id;
  private String value;

  @JsonProperty("authorizationList")
  private List<AuthorizationModel> authorizationModelList;
}

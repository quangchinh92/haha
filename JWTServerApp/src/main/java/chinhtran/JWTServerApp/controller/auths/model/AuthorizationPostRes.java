package chinhtran.JWTServerApp.controller.auths.model;

import java.util.List;
import lombok.Data;

@Data
public class AuthorizationPostRes {
  private List<AuthorizationPostModel> authorizationList;
}

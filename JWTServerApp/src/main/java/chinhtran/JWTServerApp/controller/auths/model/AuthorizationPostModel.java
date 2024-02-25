package chinhtran.JWTServerApp.controller.auths.model;

import lombok.Data;

@Data
public class AuthorizationPostModel {
    private Long id;
    private Long userId;
    private Long roleId;
}

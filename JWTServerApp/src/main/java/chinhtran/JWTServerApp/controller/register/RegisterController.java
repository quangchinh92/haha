package chinhtran.JWTServerApp.controller.register;

import chinhtran.JWTServerApp.consts.CLAIMS;
import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.controller.register.model.RegisterPostRes;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.service.RegisterService;
import chinhtran.JWTServerApp.service.impl.JwtServiceImpl;
import chinhtran.JWTServerApp.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class RegisterController {

  @Autowired private RegisterService registerService;

  @Autowired private JwtServiceImpl jwtService;

  @PostMapping
  public ResponseEntity<RegisterPostRes> post(@RequestBody RegisterPostReq req) throws Exception {

    UserEntity userEntity = registerService.register(req);

    // Create claims
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIMS.ROLES.getValue(), JsonUtils.writeValueAsString(userEntity.getRoleList()));

    // return response
    return ResponseEntity.ok(
        RegisterPostRes.builder()
            .jwt(jwtService.generateTokenWithClaims(userEntity.getUsername(), claims))
            .username(userEntity.getUsername())
            .build());
  }
}

package chinhtran.JWTServerApp.controller.auths;

import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostRes;
import chinhtran.JWTServerApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

  @Autowired private AuthenticationService suthenticationService;

  @PostMapping
  public ResponseEntity<AuthenticationPostRes> post(@RequestBody AuthenticationPostReq req) {
    return ResponseEntity.ok(suthenticationService.authenticate(req));
  }
}

package chinhtran.JWTServerApp.controller.user;

import chinhtran.JWTServerApp.controller.user.model.PasswordForgetingReq;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/password-forgeting")
public class PasswordForgetingController {
  @PostMapping
  public ResponseEntity<Void> post(@RequestBody @Valid PasswordForgetingReq req) {
    return ResponseEntity.noContent().build();
  }
}

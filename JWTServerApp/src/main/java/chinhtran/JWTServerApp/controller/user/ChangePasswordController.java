package chinhtran.JWTServerApp.controller.user;

import chinhtran.JWTServerApp.controller.BaseController;
import chinhtran.JWTServerApp.controller.user.model.ChangePassReq;
import chinhtran.JWTServerApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/changepass")
public class ChangePasswordController extends BaseController {

  private final UserService userService;

  @Autowired
  public ChangePasswordController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Void> post(@RequestBody ChangePassReq req) {
    userService.changePass(getAuthenticationToken().getUserId(), req);
    return ResponseEntity.noContent().build();
  }
}

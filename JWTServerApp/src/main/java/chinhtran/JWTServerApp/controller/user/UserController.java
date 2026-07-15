package chinhtran.JWTServerApp.controller.user;

import chinhtran.JWTServerApp.config.MyAuthenticationToken;
import chinhtran.JWTServerApp.controller.user.model.UserGetRes;
import chinhtran.JWTServerApp.controller.user.model.UserPostReq;
import chinhtran.JWTServerApp.controller.user.model.UserPostRes;
import chinhtran.JWTServerApp.controller.user.model.UserPutReq;
import chinhtran.JWTServerApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping
  public ResponseEntity<UserPostRes> post(@RequestBody @Valid UserPostReq req) {
    // return response
    return ResponseEntity.ok(userService.create(req));
  }

  @PutMapping
  public ResponseEntity<Void> put(@RequestBody UserPutReq req) {
    MyAuthenticationToken myAuthenticationToken =
        (MyAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

    userService.update(myAuthenticationToken.getUserId(), req);

    // return response
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<UserGetRes> get() {
    MyAuthenticationToken myAuthenticationToken =
        (MyAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    // return response
    return ResponseEntity.ok(userService.getById(myAuthenticationToken.getUserId()));
  }
}

package chinhtran.JWTServerApp.controller.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostRes;
import chinhtran.JWTServerApp.service.AuthorizationService;

@RestController
@RequestMapping("/api/authorization")
@PreAuthorize("hasRole('ADMIN')")
public class AuthorizationController {

    private AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PreAuthorize("hasAuthority('AUTHORIZATION:READ')")
    @PostMapping
    public ResponseEntity<AuthorizationPostRes> post(Authentication authentication,
            @RequestBody AuthorizationPostReq authenticationRequest) throws Exception {
        return ResponseEntity.ok(authorizationService.authrorize(authenticationRequest));
    }
}

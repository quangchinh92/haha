package chinhtran.JWTServerApp.controller.auths;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinhtran.JWTServerApp.controller.auths.model.AuthenticationRequestBody;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationResource;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AuthorizationController {

    @PreAuthorize("hasAuthority('AUTHORIZATION:READ')")
    @RequestMapping(value = "/api/authorization", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResource> post(Authentication authentication,
            @RequestBody AuthenticationRequestBody authenticationRequest) throws Exception {
        AuthenticationResource authenticationResource = new AuthenticationResource();
        authenticationResource.setIsValid(false);
        return ResponseEntity.ok(authenticationResource);
    }
}

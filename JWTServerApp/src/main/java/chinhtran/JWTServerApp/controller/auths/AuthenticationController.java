package chinhtran.JWTServerApp.controller.auths;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinhtran.JWTServerApp.entity.User.MyGrantedAuthority;

@RestController
public class AuthenticationController {

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResource> createAuthenticationToken(
            Authentication authentication,
            @RequestBody AuthenticationRequestBody authenticationRequest) throws Exception {
        AuthenticationResource authenticationResource = new AuthenticationResource();
        authenticationResource.setIsValid(false);
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        grantedAuthorities.stream().forEach(grantedAuthority -> {
            if (grantedAuthority instanceof MyGrantedAuthority) {
                MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) grantedAuthority;
                myGrantedAuthority.getEndpointList().stream().forEach(endpoint -> {
                    if (endpoint.getValue().equals(authenticationRequest.getEndpoint())) {
                        authenticationResource.setIsValid(true);
                    }
                });
            }
        });
        return ResponseEntity.ok(authenticationResource);
    }
}

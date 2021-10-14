package chinhtran.JWTServerApp.controller.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinhtran.JWTServerApp.utils.JwtUtils;

@RestController
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public ResponseEntity<AuthorizationResource> createAuthenticationToken(
            @RequestBody AuthorizationRequestBody authorizationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authorizationRequest.getUsername(), authorizationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        AuthorizationResource authorizationResource = new AuthorizationResource();
        authorizationResource.setJwt(
                JwtUtils.generateToken(authorizationRequest.getUsername()));
        return ResponseEntity.ok(authorizationResource);
    }
}

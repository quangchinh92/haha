package haha.controllers.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import haha.services.MyUserDetailsService;
import haha.utils.JwtUtils;
import haha.wrapper.ResponseBodyWrapper;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ResponseBodyWrapper<AuthenticationResource>> createAuthenticationToken(
            @RequestBody AuthenticationRequestBody authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        AuthenticationResource authenticationResource = new AuthenticationResource();
        authenticationResource.setJwt(
                JwtUtils.generateToken(myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername())));
        return ResponseEntity.ok(ResponseBodyWrapper.createSuccess(authenticationResource));
    }
}

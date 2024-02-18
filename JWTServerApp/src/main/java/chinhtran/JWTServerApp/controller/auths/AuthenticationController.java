package chinhtran.JWTServerApp.controller.auths;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostRes;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.service.UserService;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/api/authentication", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationPostRes> post(@RequestBody AuthenticationPostReq req) {

        // Check user is exist in DB.
        UserEntity user = userService.getUserByUsernameAndPassword(req.getUsername(), req.getPassword());

        // Create claims
        Map<String, Object> claims = new HashMap<>();
        try {
            claims.put("roles", new ObjectMapper().writeValueAsString(user.getRoleList()));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // return response
        AuthenticationPostRes res = new AuthenticationPostRes();
        res.setJwt(jwtService.generateTokenWithClaims(user.getUsername(), claims));
        res.setUsername(user.getUsername());
        return ResponseEntity.ok(res);
    }
}

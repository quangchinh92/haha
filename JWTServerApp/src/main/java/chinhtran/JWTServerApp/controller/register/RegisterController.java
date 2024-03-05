package chinhtran.JWTServerApp.controller.register;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.controller.register.model.RegisterPostRes;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.service.RegisterService;

@RestController
@RequestMapping(value = "/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<RegisterPostRes> post(@RequestBody RegisterPostReq req) throws Exception {

        UserEntity userEntity = registerService.regist(req);

        // Create claims
        Map<String, Object> claims = new HashMap<>();
        try {
            claims.put("roles", new ObjectMapper().writeValueAsString(userEntity.getRoleList()));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // return response
        RegisterPostRes res = new RegisterPostRes();
        res.setJwt(jwtService.generateTokenWithClaims(userEntity.getUsername(), claims));
        res.setUsername(userEntity.getUsername());
        return ResponseEntity.ok(res);
    }
}

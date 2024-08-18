package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.consts.CLAIMS;
import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthenticationPostRes;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.exceptions.AuthenticationException;
import chinhtran.JWTServerApp.repository.UserRepository;
import chinhtran.JWTServerApp.service.AESService;
import chinhtran.JWTServerApp.service.AuthenticationService;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  public final UserRepository userRepository;
  public final AESService aesService;
  public final JwtService jwtService;

  @Autowired
  public AuthenticationServiceImpl(
      UserRepository userRepository, AESService aesService, JwtService jwtService) {
    this.userRepository = userRepository;
    this.aesService = aesService;
    this.jwtService = jwtService;
  }

  /**
   * Authenticate user.
   *
   * @param req AuthenticationPostReq
   * @return AuthenticationPostRes
   */
  @Override
  public AuthenticationPostRes authenticate(AuthenticationPostReq req) {
    // Get user from DB
    UserEntity user =
        userRepository
            .findByUsernameAndPassword(req.getUsername(), aesService.encrypt(req.getPassword()))
            .orElseThrow(() -> new AuthenticationException(Message.AUTH_ERR_001));

    // Create claims
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIMS.ROLES.getValue(), JsonUtils.writeValueAsString(user.getRoleList()));

    // return response
    return AuthenticationPostRes.builder()
        .jwt(jwtService.generateTokenWithClaims(user.getUsername(), claims))
        .username(user.getUsername())
        .build();
  }
}

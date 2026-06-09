package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.consts.CLAIMS;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  /**
   * Extract username from token
   *
   * @param token
   * @return String
   */
  public String extractUsername(String token) {
    return JwtUtils.extractSubject(secretKey, token);
  }

  /**
   * Extract userId from token
   *
   * @param token
   * @return String
   */
  public Long extractUserId(String token) {
    return Long.parseLong(extractClaims(token).get(CLAIMS.USER_ID.getValue()).toString());
  }

  public String generateToken(String username) {
    return JwtUtils.generateToken(secretKey, username);
  }

  public void extractExpiration(String token) {
    JwtUtils.extractExpiration(secretKey, token);
  }

  public String generateTokenWithClaims(String username, Map<String, Object> claims) {
    return JwtUtils.generateTokenWithClaims(secretKey, claims, username);
  }

  private Claims extractClaims(String token) {
    return JwtUtils.extractAllClaims(secretKey, token);
  }

  @Override
  public Map<String, Object> getClaims(String token) {
    return JwtUtils.extractAllClaims(secretKey, token);
  }
}

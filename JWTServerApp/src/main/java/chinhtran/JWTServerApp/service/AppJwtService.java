package chinhtran.JWTServerApp.service;

import java.util.Map;

public interface AppJwtService {
  /**
   * Extract username from token
   *
   * @param token
   * @return String
   */
  public String extractUsername(String token);

  /**
   * Extract userId from token
   *
   * @param token
   * @return String
   */
  public Long extractUserId(String token);

  public String generateToken(String username);

  public void extractExpiration(String token);

  public String generateTokenWithClaims(String username, Map<String, Object> claims);
}

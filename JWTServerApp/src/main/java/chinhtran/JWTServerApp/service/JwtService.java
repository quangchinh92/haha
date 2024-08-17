package chinhtran.JWTServerApp.service;

import java.util.List;
import java.util.Map;

import chinhtran.JWTServerApp.entity.UserEntity.MyGrantedAuthority;

public interface JwtService {
    /**
     * Extract username from token
     *
     * @param token
     * @return String
     */
    public String extractUsername(String token);

    public String generateToken(String username);

    public void extractExpiration(String token);

    public String generateTokenWithClaims(String username, Map<String, Object> claims);

    public List<MyGrantedAuthority> getAuthorities(String token);
}

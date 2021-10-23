package chinhtran.JWTServerApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.utils.JwtUtils;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * Extract username from token
     *
     * @param token
     * @return String
     */
    public String extractUsername(String token) {
        return JwtUtils.extractUsername(secretKey, token);
    }

    public String generateToken(String username) {
        return JwtUtils.generateToken(secretKey, username);
    }

    public void extractExpiration(String token) {
        JwtUtils.extractExpiration(secretKey, token);
    }
}

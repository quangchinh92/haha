package chinhtran.JWTServerApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.entity.RoleEntity;
import chinhtran.JWTServerApp.entity.UserEntity.MyGrantedAuthority;
import chinhtran.JWTServerApp.utils.JwtUtils;
import io.jsonwebtoken.Claims;

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

    public String generateTokenWithClaims(String username, Map<String, Object> claims) {
        return JwtUtils.generateTokenWithClaims(secretKey, claims, username);
    }

    public List<MyGrantedAuthority> getAuthorities(String token) {
        try {
            List<RoleEntity> roleList = new ObjectMapper().readValue(extractClaims(token).get("roles").toString(),
                    new ObjectMapper().getTypeFactory().constructCollectionType(ArrayList.class, RoleEntity.class));
            List<MyGrantedAuthority> result = new ArrayList<>();
            roleList.forEach(role -> {
                result.add(new MyGrantedAuthority("ROLE_" + role.getValue()));
                role.getAutorizationList().forEach(authorization -> {
                    result.add(new MyGrantedAuthority(authorization.getValue()));
                });
            });
            return result;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Claims extractClaims(String token) {
        return JwtUtils.extractAllClaims(secretKey, token);
    }
}

package chinhtran.JWTServerApp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    /**
     * Get subject from claims.
     *
     * @param secretKey String
     * @param token     String
     * @return Subject String
     */
    public static String extractSubject(String secretKey, String token) {
        return extractClaim(secretKey, token, Claims::getSubject);
    }

    /**
     * Get expiration fro claims.
     *
     * @param secretKey String
     * @param token     String
     * @return Expiration String
     */
    public static Date extractExpiration(String secretKey, String token) {
        return extractClaim(secretKey, token, Claims::getExpiration);
    }

    /**
     * Get property of claims from token.
     *
     * @param <T>
     * @param secretKey      String
     * @param token          String
     * @param claimsResolver Function
     * @return Propertiy
     */
    public static <T> T extractClaim(String secretKey, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(secretKey, token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get claims from token.
     *
     * @param secretKey String
     * @param token     String
     * @return Claims
     */
    public static Claims extractAllClaims(String secretKey, String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * Generate token with username and claims is empty.
     *
     * @param secretKey String
     * @param username  String
     * @return JwtToken
     */
    public static String generateToken(String secretKey, String username) {
        Map<String, Object> claims = new HashMap<>();
        return generateTokenWithClaims(secretKey, claims, username);
    }

    /**
     * Generate token with username and claims.
     *
     * @param secretKey String
     * @param claims    Map<String, Object>
     * @param subject   String
     * @return JwtToken
     */
    public static String generateTokenWithClaims(String secretKey, Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}

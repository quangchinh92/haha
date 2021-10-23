package chinhtran.JWTServerApp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    public static String extractUsername(String secretKey, String token) {
        return extractClaim(secretKey, token, Claims::getSubject);
    }

    public static Date extractExpiration(String secretKey, String token) {
        return extractClaim(secretKey, token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String secretKey, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(secretKey, token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String secretKey, String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public static String generateToken(String secretKey, String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(secretKey, claims, username);
    }

    private static String createToken(String secretKey, Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}

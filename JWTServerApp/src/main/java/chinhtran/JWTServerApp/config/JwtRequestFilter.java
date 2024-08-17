package chinhtran.JWTServerApp.config;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.entity.UserEntity.MyGrantedAuthority;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;
import chinhtran.JWTServerApp.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION = "Authorization";
    private final static String AUTHORIZATION_TYPE = "Bearer ";

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authorizationHeader.substring(AUTHORIZATION_TYPE.length());
        try {
            jwtService.extractExpiration(jwtToken);
        } catch (SignatureException ex) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(createForbiddenApiError()));
            return;
        } catch (ExpiredJwtException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(createUnauthorizedApiErrorExpired()));
            return;
        }
        String username = jwtService.extractUsername(jwtToken);
        if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

            List<MyGrantedAuthority> authorities = jwtService.getAuthorities(jwtToken);

            MyAuthenticationToken myAuthenticationToken = new MyAuthenticationToken(username,
                    authorities);
            SecurityContextHolder.getContext().setAuthentication(myAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private ApiError createUnauthorizedApiErrorExpired() {
        Error error = new Error();
        error.setCode("3");
        error.setMessage("Token is expired.");
        return new ApiError(HttpStatus.UNAUTHORIZED, error);
    }

    private ApiError createForbiddenApiError() {
        Error error = new Error();
        error.setCode("1");
        error.setMessage("Token is wrong.");
        return new ApiError(HttpStatus.FORBIDDEN, error);
    }
}

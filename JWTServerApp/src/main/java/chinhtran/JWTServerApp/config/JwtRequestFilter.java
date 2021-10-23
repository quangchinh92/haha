package chinhtran.JWTServerApp.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import chinhtran.JWTServerApp.exceptions.ApiError;
import chinhtran.JWTServerApp.exceptions.Error;
import chinhtran.JWTServerApp.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION = "Authorization";
    private final static String AUTHORIZATION_TYPE = "Bearer ";

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(authorizationHeader)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(createUnauthorizedApiError()));
            return;
        }
        if (!authorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(createUnauthorizedApiErrorWrongFormat()));
            return;
        }
        String jwtToken = "";
        jwtToken = authorizationHeader.substring(AUTHORIZATION_TYPE.length());
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
        String username = "";
        username = jwtService.extractUsername(jwtToken);
        if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private ApiError createUnauthorizedApiError() {
        Error error = new Error();
        error.setCode(1);
        error.setMessage("Do not found header '" + AUTHORIZATION + "'.");
        return new ApiError(HttpStatus.UNAUTHORIZED, error);
    }

    private ApiError createUnauthorizedApiErrorWrongFormat() {
        Error error = new Error();
        error.setCode(2);
        error.setMessage(AUTHORIZATION + " starts with '" + AUTHORIZATION_TYPE + "'.");
        return new ApiError(HttpStatus.UNAUTHORIZED, error);
    }

    private ApiError createUnauthorizedApiErrorExpired() {
        Error error = new Error();
        error.setCode(3);
        error.setMessage("Token is expired.");
        return new ApiError(HttpStatus.UNAUTHORIZED, error);
    }

    private ApiError createForbiddenApiError() {
        Error error = new Error();
        error.setCode(1);
        error.setMessage("Token is wrong.");
        return new ApiError(HttpStatus.FORBIDDEN, error);
    }
}

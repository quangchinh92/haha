package chinhtran.JWTServerApp.config;

import chinhtran.JWTServerApp.consts.CLAIMS;
import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;
import chinhtran.JWTServerApp.repository.entity.UserEntity.MyGrantedAuthority;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Read and check JWT token from the request before calling controller. */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION = "Authorization";
  private static final String AUTHORIZATION_TYPE = "Bearer ";

  @Autowired private JwtService jwtService;
  @Autowired private MessageSource messageSource;
  @Autowired private UserService userService;

  /** Perform a read and verification of the JWT token. */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // Get authorization from header.
    final String authorizationHeader = request.getHeader(AUTHORIZATION);

    // Check authorization token.
    if (StringUtils.isBlank(authorizationHeader)
        || !authorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
      filterChain.doFilter(request, response);
      return;
    }

    // Get JWT token from authorization.
    String jwtToken = authorizationHeader.substring(AUTHORIZATION_TYPE.length());

    // Validate JWT token.
    try {
      jwtService.extractExpiration(jwtToken);
    } catch (SignatureException ex) {
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.getWriter().write(new ObjectMapper().writeValueAsString(createForbiddenApiError()));
      return;
    } catch (ExpiredJwtException ex) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response
          .getWriter()
          .write(new ObjectMapper().writeValueAsString(createUnauthorizedApiErrorExpired()));
      return;
    }

    Long userId = jwtService.extractUserId(jwtToken);
    // Check valid token based on changing password date.

    Map<String, Object> claims = jwtService.getClaims(jwtToken);
    Date updatedPasswordDate = new Date((Long) claims.get(CLAIMS.UPDATED_PASSWORD_DATE.getValue()));
    if (updatedPasswordDate.before(userService.getById(userId).getUpdatedPasswordDate())) {
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.getWriter().write(new ObjectMapper().writeValueAsString(createForbiddenApiError()));
      return;
    }

    String username = jwtService.extractUsername(jwtToken);

    // Set user infor in context.
    if (StringUtils.isNotBlank(username)
        && SecurityContextHolder.getContext().getAuthentication() == null) {

      List<MyGrantedAuthority> authorities = Arrays.asList(new MyGrantedAuthority("ROLE_USER"));

      MyAuthenticationToken myAuthenticationToken =
          new MyAuthenticationToken(userId, username, authorities);
      SecurityContextHolder.getContext().setAuthentication(myAuthenticationToken);
    }
    filterChain.doFilter(request, response);
  }

  /**
   * An API error is generated, which is related to unauthorized access.
   *
   * @return ApiError
   */
  private ApiError createUnauthorizedApiErrorExpired() {
    return new ApiError(
        Error.builder()
            .code(Message.AUTH_ERR_002)
            .message(messageSource.getMessage(Message.AUTH_ERR_002, null, Locale.ENGLISH))
            .build());
  }

  /**
   * An API error is generated, which is related to forbidden access.
   *
   * @return ApiError
   */
  private ApiError createForbiddenApiError() {
    return new ApiError(
        Error.builder()
            .code(Message.AUTH_ERR_003)
            .message(messageSource.getMessage(Message.AUTH_ERR_003, null, Locale.ENGLISH))
            .build());
  }
}

package haha.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import haha.services.MyUserDetailsService;
import haha.utils.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String AUTHORIZATION_TYPE = "Bearer ";

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String username = "";
        String jwtToken = "";
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
            jwtToken = authorizationHeader.substring(AUTHORIZATION_TYPE.length());
            username = JwtUtils.extractUsername(jwtToken);
        }
        if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
            if (JwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

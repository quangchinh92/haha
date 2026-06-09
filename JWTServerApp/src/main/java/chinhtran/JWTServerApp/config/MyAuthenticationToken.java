package chinhtran.JWTServerApp.config;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/** TODO */
public class MyAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private final Object principal;
  private final Long userId;

  public MyAuthenticationToken(
      Long userId, Object principal, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.userId = userId;
    super.setAuthenticated(true); // must use super, as we override
  }

  @Override
  public Object getCredentials() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.principal;
  }

  @Override
  public String getName() {
    return super.getName();
  }

  public Long getUserId() {
    return this.userId;
  }
}

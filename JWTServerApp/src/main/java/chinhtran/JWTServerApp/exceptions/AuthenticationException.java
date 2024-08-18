package chinhtran.JWTServerApp.exceptions;

import java.util.List;

public class AuthenticationException extends AbstractException {

  private static final long serialVersionUID = 1L;

  public AuthenticationException(String code) {
    super(code);
  }

  public AuthenticationException(String code, List<String> args) {
    super(code, args);
  }
}

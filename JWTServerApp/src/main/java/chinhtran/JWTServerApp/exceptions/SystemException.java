package chinhtran.JWTServerApp.exceptions;

import java.util.List;

public class SystemException extends AbstractException {

  private static final long serialVersionUID = 1L;

  public SystemException(String code) {
    super(code);
  }

  public SystemException(String code, List<String> args) {
    super(code, args);
  }
}

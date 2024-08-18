package chinhtran.JWTServerApp.exceptions;

import java.util.List;

public class BusinessException extends AbstractException {

  private static final long serialVersionUID = 1L;

  public BusinessException(String code) {
    super(code);
  }

  public BusinessException(String code, List<String> args) {
    super(code, args);
  }
}

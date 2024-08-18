package chinhtran.JWTServerApp.exceptions;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  @Getter @Setter private String code;

  @Getter @Setter private List<String> args;

  public AbstractException(String code) {
    this.code = code;
  }

  public AbstractException(String code, List<String> args) {
    this.code = code;
    this.args = args;
  }
}

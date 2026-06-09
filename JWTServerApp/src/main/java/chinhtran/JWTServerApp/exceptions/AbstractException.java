package chinhtran.JWTServerApp.exceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public abstract class AbstractException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  @Getter private String code;

  private List<String> args;

  public AbstractException(String code) {
    this.code = code;
  }

  public AbstractException(String code, List<String> args) {
    this.code = code;
    this.args = new ArrayList<>(args);
  }

  /**
   * getArgs
   *
   * @return List<String>
   */
  public List<String> getArgs() {
    return new ArrayList<>(this.args);
  }
}

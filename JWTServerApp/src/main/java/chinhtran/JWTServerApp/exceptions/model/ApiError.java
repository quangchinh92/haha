package chinhtran.JWTServerApp.exceptions.model;

import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public class ApiError {
  private List<Error> errors;

  public ApiError(List<Error> errors) {
    this.errors = errors;
  }

  public ApiError(Error error) {
    errors = Arrays.asList(error);
  }
}

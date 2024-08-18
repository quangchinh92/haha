package chinhtran.JWTServerApp.exceptions.model;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
  private HttpStatus status;
  private List<Error> errors;

  public ApiError(HttpStatus status, List<Error> errors) {
    this.status = status;
    this.errors = errors;
  }

  public ApiError(HttpStatus status, Error error) {
    super();
    this.status = status;
    errors = Arrays.asList(error);
  }
}

package chinhtran.JWTServerApp.exceptions.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiError {
  private List<Error> errors;

  public ApiError(List<Error> errors) {
    this.errors = new ArrayList<>(errors);
  }

  public ApiError(Error error) {
    errors = Arrays.asList(error);
  }

  public List<Error> getErrors() {
    return new ArrayList<>(this.errors);
  }
}

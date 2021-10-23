package chinhtran.JWTServerApp.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

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

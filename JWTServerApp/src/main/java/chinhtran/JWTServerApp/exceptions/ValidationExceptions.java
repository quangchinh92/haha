package chinhtran.JWTServerApp.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptions extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<Error> errors = new ArrayList<Error>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Error myError = new Error();
            myError.setCode(2);
            myError.setMessage(error.getField() + " " + error.getDefaultMessage());
            errors.add(myError);
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(apiError, headers, status);
    }

    @Override
    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error();
        error.setCode(1);
        error.setMessage("Can not read request body");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, headers, status);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ApiError> handleConflict(Exception ex, WebRequest request) {
        Error error = new Error();
        error.setCode(1);
        error.setMessage("Something went wrong!");
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

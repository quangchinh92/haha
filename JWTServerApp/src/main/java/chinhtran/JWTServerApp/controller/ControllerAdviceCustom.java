package chinhtran.JWTServerApp.controller;

import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.AbstractException;
import chinhtran.JWTServerApp.exceptions.AuthenticationException;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdviceCustom {

  @Autowired private MessageSource messageSource;

  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<Error> errors = new ArrayList<Error>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(
          Error.builder()
              .code("2")
              .message(error.getField() + " " + error.getDefaultMessage())
              .build());
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);
    return new ResponseEntity<>(apiError, headers, status);
  }

  public final ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ApiError apiError =
        new ApiError(
            HttpStatus.BAD_REQUEST,
            Error.builder().code("1").message("Can not read request body").build());
    return new ResponseEntity<>(apiError, headers, status);
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<ApiError> handleConflict(Exception ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            Error.builder()
                .code(Message.SYS_ERR_001)
                .message(getMessage(Message.SYS_ERR_001, new ArrayList<>()))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {AbstractException.class})
  protected ResponseEntity<ApiError> handleAbstractException(
      AbstractException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            Error.builder()
                .code(ex.getCode())
                .message(getMessage(ex.getCode(), ex.getArgs()))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {AuthenticationException.class})
  protected ResponseEntity<ApiError> handleAuthenticationException(
      AuthenticationException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            HttpStatus.UNAUTHORIZED,
            Error.builder()
                .code(ex.getCode())
                .message(getMessage(ex.getCode(), ex.getArgs()))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Get message by code
   *
   * @param code String
   * @return message String
   */
  private String getMessage(String code, List<String> args) {
    return messageSource.getMessage(code, args != null ? args.toArray() : null, Locale.ENGLISH);
  }
}

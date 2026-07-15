package chinhtran.JWTServerApp.controller;

import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.AbstractException;
import chinhtran.JWTServerApp.exceptions.AuthenticationException;
import chinhtran.JWTServerApp.exceptions.BusinessException;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdviceCustom {

  @Autowired private MessageSource messageSource;

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<ApiError> handleConflict(Exception ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            Error.builder()
                .code(Message.SYS_ERR_001)
                .message(getMessage(Message.SYS_ERR_001, null))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {AbstractException.class})
  protected ResponseEntity<ApiError> handleAbstractException(
      AbstractException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
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
            Error.builder()
                .code(ex.getCode())
                .message(getMessage(ex.getCode(), ex.getArgs()))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = {BusinessException.class})
  protected ResponseEntity<ApiError> handleAuthenticationException(
      BusinessException ex, WebRequest request) {
    ApiError apiError =
        new ApiError(
            Error.builder()
                .code(ex.getCode())
                .message(getMessage(ex.getCode(), ex.getArgs()))
                .build());
    return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    List<Error> errors = new ArrayList<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error ->
                errors.add(
                    Error.builder()
                        .code(error.getField())
                        .message(error.getDefaultMessage())
                        .build()));

    return new ResponseEntity<>(new ApiError(errors), HttpStatus.BAD_REQUEST);
  }
}

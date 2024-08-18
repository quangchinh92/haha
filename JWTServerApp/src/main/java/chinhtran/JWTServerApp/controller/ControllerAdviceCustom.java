package chinhtran.JWTServerApp.controller;

import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.AbstractException;
import chinhtran.JWTServerApp.exceptions.AuthenticationException;
import chinhtran.JWTServerApp.exceptions.BusinessException;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    List<Error> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(
                error ->
                    Error.builder()
                        .code(Message.BAD_REQ_ERR_002)
                        .message(error.getField() + " " + error.getDefaultMessage())
                        .build())
            .collect(Collectors.toList());
    return new ResponseEntity<>(new ApiError(errors), headers, status);
  }

  public final ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ApiError apiError =
        new ApiError(
            Error.builder()
                .code(Message.BAD_REQ_ERR_001)
                .message(getMessage(Message.BAD_REQ_ERR_001, null))
                .build());
    return new ResponseEntity<>(apiError, headers, status);
  }

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
}

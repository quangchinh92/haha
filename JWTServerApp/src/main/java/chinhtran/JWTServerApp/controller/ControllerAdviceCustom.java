package chinhtran.JWTServerApp.controller;

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

import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.exceptions.AbstractException;
import chinhtran.JWTServerApp.exceptions.AuthenticationException;
import chinhtran.JWTServerApp.exceptions.SystemException;
import chinhtran.JWTServerApp.exceptions.model.ApiError;
import chinhtran.JWTServerApp.exceptions.model.Error;

@ControllerAdvice
public class ControllerAdviceCustom {

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<Error> errors = new ArrayList<Error>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Error myError = new Error();
            myError.setCode("2");
            myError.setMessage(error.getField() + " " + error.getDefaultMessage());
            errors.add(myError);
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(apiError, headers, status);
    }

    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error();
        error.setCode("1");
        error.setMessage("Can not read request body");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, headers, status);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ApiError> handleConflict(Exception ex, WebRequest request) {
        Error error = new Error();
        error.setCode(Message.SYS_ERR_001);
        error.setMessage(getMessage(Message.SYS_ERR_001, new ArrayList<>()));
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { AbstractException.class })
    protected ResponseEntity<ApiError> handleAbstractException(AbstractException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(ex.getCode());
        error.setMessage(getMessage(ex.getCode(), ex.getArgs()));
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    protected ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(ex.getCode());
        error.setMessage(getMessage(ex.getCode(), ex.getArgs()));
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, error);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get message by code
     * @param code String
     * @return message String
     */
    private String getMessage(String code, List<String> args) {
        return messageSource.getMessage(code, args != null ? args.toArray() : null, Locale.ENGLISH);
    }
}

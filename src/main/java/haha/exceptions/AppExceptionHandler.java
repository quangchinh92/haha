package haha.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import haha.wrapper.ResponseBodyWrapper;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MESS_SOMETHING_WENT_WRONG = "Something went wrong!";

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ResponseBodyWrapper<?>> handleAnyException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<ResponseBodyWrapper<?>>(ResponseBodyWrapper
                .createError(MESS_SOMETHING_WENT_WRONG),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

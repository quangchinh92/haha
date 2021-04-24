package haha.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/runtimeException")
    public ResponseEntity<Object> throwRuntimeException() {
        throw new RuntimeException("Response of request will throw RuntimeException!");
    }
}

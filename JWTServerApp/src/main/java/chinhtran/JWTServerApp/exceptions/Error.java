package chinhtran.JWTServerApp.exceptions;

import lombok.Data;

@Data
public class Error {
    private int code;
    private String message;
}

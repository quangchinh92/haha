package chinhtran.JWTServerApp.exceptions.model;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String message;
}

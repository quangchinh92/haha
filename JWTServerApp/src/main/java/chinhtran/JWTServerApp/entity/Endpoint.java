package chinhtran.JWTServerApp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Endpoint {
    @Id
    private Long id;

    private String value;
}

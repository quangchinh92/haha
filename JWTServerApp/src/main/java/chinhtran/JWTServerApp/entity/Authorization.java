package chinhtran.JWTServerApp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Authorization implements Serializable {

    @Id
    private Long id;

    private String value;

}

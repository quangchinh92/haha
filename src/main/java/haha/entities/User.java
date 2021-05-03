package haha.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import haha.enums.DELETED;
import lombok.Data;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Date birthDate;

    @Column()
    private String identityCard;

    @Column()
    private String idCard;

    @Column(nullable = false)
    private String jwt;

    @Column(nullable = false)
    private Integer actives;

    @Column(nullable = false)
    private Timestamp createdAt;

    public User setNew() {
        setActives(DELETED.NO.getValue());
        setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public User updateActives() {
        setActives(DELETED.YES.getValue());
        return this;
    }
}

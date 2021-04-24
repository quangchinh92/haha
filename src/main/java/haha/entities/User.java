package haha.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import haha.enums.DELETED;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Hotel> hotelList;

    @Column(nullable = false)
    private Integer deleted;

    @Column(nullable = false)
    private Timestamp createdAt;

    public User setNew() {
        setDeleted(DELETED.NO.getValue());
        setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public User updateDeleted() {
        setDeleted(DELETED.YES.getValue());
        return this;
    }
}

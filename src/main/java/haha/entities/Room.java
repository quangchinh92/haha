package haha.entities;

import java.io.Serializable;
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
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer deleted;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    public Room setNew() {
        setDeleted(DELETED.NO.getValue());
        setCreatedAt(new Timestamp(System.currentTimeMillis()));
        setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public Room updateDeleted() {
        setDeleted(DELETED.YES.getValue());
        setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return this;
    }
}

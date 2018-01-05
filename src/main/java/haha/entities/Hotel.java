package haha.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    private City city;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String zip;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "hotel")
    private Set<Review> reviewList;

    public City getCity() {
        return this.city;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZip() {
        return this.zip;
    }
}

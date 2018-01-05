package haha.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import haha.constant.RATING;
import haha.constant.TRIP_TYPE;

@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", referencedColumnName = "ID")
    private Hotel hotel;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private RATING rating;

    @Column
    private Date checkInDate;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private TRIP_TYPE tripType;

    @Column(nullable = false)
    private String title;

    @Column(length = 5000)
    private String details;

    public Hotel getHotel() {
        return this.hotel;
    }

    public RATING getRating() {
        return this.rating;
    }

    public void setRating(RATING rating) {
        this.rating = rating;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public TRIP_TYPE getTripType() {
        return this.tripType;
    }

    public void setTripType(TRIP_TYPE tripType) {
        this.tripType = tripType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

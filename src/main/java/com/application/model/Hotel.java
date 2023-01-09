package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.*;

import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "star_rating")
    @Min(0)
    @Max(5)
    @Positive
    private int starRating;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Hotel() {

    }


    @Builder
    public Hotel(long id, String name, int starRating, Country country) {
        this.id = id;
        this.name = name;
        this.starRating = starRating;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id && starRating == hotel.starRating && name.equals(hotel.name) && country.equals(hotel.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, starRating, country);
    }
}

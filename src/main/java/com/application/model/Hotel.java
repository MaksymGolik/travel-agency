package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.*;


@Data
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
}

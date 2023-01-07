package com.application.model;

import javax.persistence.*;

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

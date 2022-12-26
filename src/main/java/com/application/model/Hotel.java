package com.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
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

    public Hotel(String name, int starRating, Country country) {
        this.name = name;
        this.starRating = starRating;
        this.country = country;
    }
}

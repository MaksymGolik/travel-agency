package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "name", nullable = false)
    private String name;

    public Country() {
    }

    @Builder
    public Country (String name) {
        this.name = name;
    }
}

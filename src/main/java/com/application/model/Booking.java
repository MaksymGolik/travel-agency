package com.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_in", nullable = false)
    private Date date_in;

    @Column(name = "date_out", nullable = false)
    private Date date_out;


    @Column(name = "price")
    private double price;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
    @JoinColumn(name = "room_id")
    private List<Room> room;






}

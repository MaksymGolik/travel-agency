package com.application.model;


import javax.persistence.*;

import lombok.*;


@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "number_of_room", nullable = false, unique = true)
    private int numberOfRoom;

    @Column(name = "people_capacity", nullable = false)
    private int peopleCapacity;


    @Column(name = "price", nullable = false)
    private double pricePerRoom;


    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @Builder
    public Room(long id, int numberOfRoom, int peopleCapacity, double pricePerRoom, RoomType roomType, Hotel hotel) {
        this.id = id;
        this.numberOfRoom = numberOfRoom;
        this.peopleCapacity = peopleCapacity;
        this.pricePerRoom = pricePerRoom;
        this.roomType = roomType;
        this.hotel = hotel;
    }

    public Room() {

    }
}

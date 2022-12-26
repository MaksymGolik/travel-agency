package com.application.model;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "number_of_room", nullable = false, unique = true)
    private int numberOfRoom;

    @Column(name = "people_capacity", nullable = false)
    private int peopleCapacity;


    @Column(name = "price", nullable = false)
    private double pricePerRoom;


    @JoinColumn(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Room() {
    }

    public Room(Long id, int numberOfRoom, int peopleCapacity, double pricePerRoom, RoomType roomType, Hotel hotel) {
        this.id = id;
        this.numberOfRoom = numberOfRoom;
        this.peopleCapacity = peopleCapacity;
        this.pricePerRoom = pricePerRoom;
        this.roomType = roomType;
        this.hotel = hotel;
    }

}

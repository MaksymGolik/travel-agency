package com.application.model;


import javax.persistence.*;

import lombok.*;


@ToString
@Getter
@Setter
@Entity
@Table(name = "rooms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


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
}

package com.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;


@ToString
@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(name = "date_in", nullable = false)
    private LocalDateTime dateIn;


    @Column(name = "date_out", nullable = false)
    private LocalDateTime dateOut;


    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    public Booking(LocalDateTime dateIn, LocalDateTime dateOut, double totalPrice, User user, Room room) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.totalPrice = totalPrice;
        this.user = user;
        this.room = room;
    }


    public Booking() {

    }
}

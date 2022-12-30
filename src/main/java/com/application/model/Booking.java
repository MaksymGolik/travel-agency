package com.application.model;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;


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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking_rooms",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms;


    public Booking(LocalDateTime dateIn, LocalDateTime dateOut, double totalPrice, User user, List<Room> rooms) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.totalPrice = totalPrice;
        this.user = user;
        this.rooms = rooms;
    }


    public Booking() {

    }
}

package com.application.dto;

import com.application.model.Hotel;
import com.application.model.RoomType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class RoomResponse {


    private long id;

    private int numberOfRoom;

    private int peopleCapacity;

    private double pricePerRoom;

    private RoomType roomType;

    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomResponse that = (RoomResponse) o;
        return peopleCapacity == that.peopleCapacity && Double.compare(that.pricePerRoom, pricePerRoom) == 0 && roomType == that.roomType && hotel.equals(that.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peopleCapacity, pricePerRoom, roomType, hotel);
    }
}

package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private long id;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
    private double totalPrice;
    private UserResponse guest;
    List<RoomResponse> rooms;
}

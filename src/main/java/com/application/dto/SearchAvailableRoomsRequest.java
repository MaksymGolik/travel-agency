package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SearchAvailableRoomsRequest {
    String country;
    String hotelName;
    String dateIn;
    String dateOut;
}

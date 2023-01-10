package com.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookingCreateRequest {
    String dateIn;
    String dateOut;

  List<Long> roomIdList;
}

package com.kgs.RoomReservationApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private long id;
    private RoomType roomType;
    private long hotelId;
    private int maxPeopleNumber;
    private float dailyPrice;
    private String description;
}

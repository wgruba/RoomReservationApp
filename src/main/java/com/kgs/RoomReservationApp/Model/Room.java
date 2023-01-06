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
  private double dailyPrice;
  private String description;
}

package com.kgs.RoomReservationApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
  private long id;
  private String roomType;
  private long hotelId;
  private int maxPeopleNumber;
  private double dailyPrice;
  private String description;
}

package com.kgs.RoomReservationApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hotel {
  private long id;
  private String name;
  private String address;
}

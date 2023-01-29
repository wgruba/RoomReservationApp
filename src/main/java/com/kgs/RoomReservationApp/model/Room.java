package com.kgs.RoomReservationApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room {
  @Id
  private long id;
  private String roomType;
  private long hotelId;
  private int maxPeopleNumber;
  private double dailyPrice;
  private String description;
}

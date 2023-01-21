package com.kgs.RoomReservationApp.Model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
  private long id;
  private long userId;
  private Instant start;
  private Instant end;
  private ReservationStatus status;
  private String comments;
}

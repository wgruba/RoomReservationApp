package com.kgs.RoomReservationApp.model;

import java.time.Instant;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;

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

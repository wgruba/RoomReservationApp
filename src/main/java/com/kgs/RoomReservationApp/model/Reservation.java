package com.kgs.RoomReservationApp.model;

import java.time.Instant;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
  @Id
  private long id;
  private long userId;
  private Instant start;
  private Instant end;
  private ReservationStatus status;
  private String comments;

}

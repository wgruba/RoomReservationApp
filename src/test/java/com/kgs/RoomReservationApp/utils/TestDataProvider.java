package com.kgs.RoomReservationApp.utils;

import com.kgs.RoomReservationApp.model.Reservation;
import com.kgs.RoomReservationApp.model.ReservationStatus;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.time.Duration;
import java.time.Instant;

public class TestDataProvider {

  public static ReservationWithDetails getReservationWithDetails() {
    return new ReservationWithDetails(
        new Reservation(
            1,
            1,
            Instant.EPOCH,
            Instant.EPOCH.plus(Duration.ofDays(2)),
            ReservationStatus.ACTIVE,
            "comments"),
        1,
        100.0,
        "Regular");
  }
}

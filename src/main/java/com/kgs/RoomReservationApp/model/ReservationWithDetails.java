package com.kgs.RoomReservationApp.model;

import java.time.Duration;

public record ReservationWithDetails(
    Reservation reservation, int peopleNumber, double dailyRoomPrice, String roomType) {

  public long getStayDays() {
    return Duration.between(reservation.getStart(), reservation.getEnd()).toDays() + 1;
  }

  public double getFullPayment() {
    return dailyRoomPrice * getStayDays();
  }
}

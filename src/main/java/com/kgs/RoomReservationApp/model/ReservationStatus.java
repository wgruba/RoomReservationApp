package com.kgs.RoomReservationApp.model;

public enum ReservationStatus {
  SUSPENDED(0),
  ACTIVE(1),
  FINISHED(2),
  CANCELLED(3);

  private int number;

  ReservationStatus(int number) {}

  public static ReservationStatus getReservationStatusForNumber(int number) {
    return switch (number) {
      case 0 -> SUSPENDED;
      case 1 -> ACTIVE;
      case 2 -> FINISHED;
      case 3 -> CANCELLED;
      default -> null;
    };
  }
}

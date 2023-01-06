package com.kgs.RoomReservationApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {
    Single(1),
    Double(2),
    Regular(3);

    private int number;

    static RoomType getRoomTypeForNumber(int number) {
        return switch (number) {
            case 1 -> Single;
            case 2 -> Double;
            default -> Regular;
        };
    }
}

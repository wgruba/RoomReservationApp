package com.kgs.RoomReservationApp.Model;

import lombok.Data;

@Data
public abstract class User {
    private String userName;
    private String password;
    private boolean active;
    private String roles;
}

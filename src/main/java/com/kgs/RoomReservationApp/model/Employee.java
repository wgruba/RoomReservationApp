package com.kgs.RoomReservationApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends User{
    private long id;
    private String employeeId;
    private String password;
    private boolean isActive;

    @Override
    public String getUserName(){
        return this.employeeId;
    }
    @Override
    public String getRoles() {
        return "EMPLOYEE";
    }
    @Override
    public boolean isActive() {
        return true;
    }

}

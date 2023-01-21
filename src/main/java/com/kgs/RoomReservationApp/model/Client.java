package com.kgs.RoomReservationApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {

  private long id;
  private String email;
  private String fistName;
  private String lastName;
  private String password;
  private String phoneNumber;

  @Override
  public String getRoles() {
    return "CLIENT";
  }

  @Override
  public boolean isActive() {
    return true;
  }
}

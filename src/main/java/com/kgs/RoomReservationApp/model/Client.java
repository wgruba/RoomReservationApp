package com.kgs.RoomReservationApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {
  @Id
  private long id;
  private String email;
  private String fistName;
  private String lastName;
  private String password;
  private String phoneNumber;

  @Override
  public String getUserName(){
    return this.email;
  }

  @Override
  public String getRoles() {
    return "CLIENT";
  }

  @Override
  public boolean isActive() {
    return true;
  }
}

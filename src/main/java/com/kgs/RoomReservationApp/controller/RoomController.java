package com.kgs.RoomReservationApp.controller;

import com.kgs.RoomReservationApp.model.Room;
import com.kgs.RoomReservationApp.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @GetMapping("/rooms")
  public List<Room> getAll() {
    return roomService.getAll();
  }
}

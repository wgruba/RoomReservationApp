package com.kgs.RoomReservationApp.controller;

import com.kgs.RoomReservationApp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping("/reservations/")
  public String getAll(Model model) {
    var reservations = reservationService.getAllForClient(1L);

    model.addAttribute("reservations", reservations);

    return "reservations/index";
  }
}

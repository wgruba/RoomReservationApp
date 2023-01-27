package com.kgs.RoomReservationApp.controller;

import com.kgs.RoomReservationApp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping("/reservations/")
  public String getAll(Model model) {
    var reservations = reservationService.getAllForClient(1L);
    model.addAttribute("reservations", reservations);

    return "reservations/index";
  }

  @GetMapping("/reservations/details")
  public String details(@RequestParam("id") long inputId , Model model) {
    model.addAttribute("reservation", reservationService.getById(inputId));

    return "/reservations/details";
  }

  @GetMapping("/reservations/cancel")
  public String cancel(@RequestParam("id") long inputId) {
    reservationService.cancel(inputId);
    return "redirect:/reservations/details?id=" + inputId;
  }
}

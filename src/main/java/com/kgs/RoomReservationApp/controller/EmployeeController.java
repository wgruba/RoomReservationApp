package com.kgs.RoomReservationApp.controller;

import com.kgs.RoomReservationApp.service.ClientService;
import com.kgs.RoomReservationApp.service.ReservationService;
import com.kgs.RoomReservationApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final RoomService roomService;

    @GetMapping("/employees/")
    public String getAll(Model model) {
        var reservations = reservationService.getAllForClient(1L);

        model.addAttribute("reservations", reservations);

        return "employees/index";
    }

    @GetMapping("/employees/details")
    public String details(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("reservation", reservationService.getById(inputId));
        model.addAttribute("client", clientService.findClientAssignToReservation(inputId));
        return "/employees/details";
    }
}

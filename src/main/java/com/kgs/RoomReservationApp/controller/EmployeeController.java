package com.kgs.RoomReservationApp.controller;

import com.kgs.RoomReservationApp.model.Client;
import com.kgs.RoomReservationApp.model.Reservation;
import com.kgs.RoomReservationApp.model.Room;
import com.kgs.RoomReservationApp.service.ClientService;
import com.kgs.RoomReservationApp.service.ReservationService;
import com.kgs.RoomReservationApp.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final RoomService roomService;

    @GetMapping("/employees/")
    public String getAll(Model model) {
        var reservations = reservationService.getAll();
        model.addAttribute("reservations", reservations);
        return "employees/index";
    }

    @GetMapping("/employees/details")
    public String details(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("reservation", reservationService.getById(inputId));
        model.addAttribute("client", clientService.findClientAssignToReservation(inputId));
        return "/employees/details";
    }
    @GetMapping("/employees/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("client", clientService.findClientAssignToReservation(id));
        model.addAttribute("reservation", reservationService.getById(id));
        model.addAttribute("reservation_reservation", reservationService.getById(id).reservation());
        model.addAttribute("room",roomService.getRoomForReservation(id).get());
        return "/employees/edit";
    }

    @RequestMapping(value = {"/employees/edit"})
    public String edit(@ModelAttribute("client") Client client, @ModelAttribute("reservation_reservation") Reservation reservation,@ModelAttribute("room") Room room) {
        clientService.updateClient(client);
        reservationService.updateReservation(reservation);
        roomService.updateReservedRoom(room, reservation.getId());
        return "redirect:/employees/";
    }

}

package com.kgs.RoomReservationApp.Controller;

import com.kgs.RoomReservationApp.Model.Room;
import com.kgs.RoomReservationApp.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return roomService.getAll();
    }
}

package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.RoomDao;
import com.kgs.RoomReservationApp.model.Room;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomDao roomDao;

  public List<Room> getAll() {
    return roomDao.getAll();
  }

  public void updateReservedRoom(Room room,long reservationId){
      roomDao.updateReservedRoom(room,reservationId);
  }

  public Optional<Room> getRoomForReservation(long reservationId){
    return roomDao.getRoomForReservation(reservationId);
  }



}

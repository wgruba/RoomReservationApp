package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.RoomDao;
import com.kgs.RoomReservationApp.model.Room;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomDao roomDao;

  public List<Room> getAll() {
    return roomDao.getAll();
  }}

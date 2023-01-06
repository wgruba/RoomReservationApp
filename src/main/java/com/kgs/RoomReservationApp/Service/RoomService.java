package com.kgs.RoomReservationApp.Service;

import com.kgs.RoomReservationApp.Dao.RoomDao;
import com.kgs.RoomReservationApp.Model.Room;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomDao roomDao;

  public List<Room> getAll() {
    return roomDao.getAll();
  }
}

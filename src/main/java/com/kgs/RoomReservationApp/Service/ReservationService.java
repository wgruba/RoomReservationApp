package com.kgs.RoomReservationApp.Service;

import com.kgs.RoomReservationApp.Dao.ReservationDao;
import com.kgs.RoomReservationApp.Model.ReservationWithDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationDao reservationDao;

  public List<ReservationWithDetails> getAllForClient(long clientId) {
    return reservationDao.getAllWithDetailsByClientId(clientId);
  }
}

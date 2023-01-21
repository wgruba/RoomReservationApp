package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.ReservationDao;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
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

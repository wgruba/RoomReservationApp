package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.ReservationDao;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationDao reservationDao;
  private List<ReservationWithDetails> reservations;

  public List<ReservationWithDetails> getAllForClient(long clientId) {
    reservations = reservationDao.getAllWithDetailsByClientId(clientId);
    return reservations;
  }

  public ReservationWithDetails getById(long reservationId) {
    Optional<ReservationWithDetails> reservation;

    reservation =
        reservations != null
            ? reservations.stream()
                .filter(reservations -> reservations.reservation().getId() == reservationId)
                .findAny()
            : reservationDao.getWithDetailsById(reservationId);

    return reservation.orElseGet(null);
  }
}

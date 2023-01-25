package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.ReservationDao;
import com.kgs.RoomReservationApp.model.ReservationStatus;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationDao reservationDao;
  private List<ReservationWithDetails> cachedReservations;

  public List<ReservationWithDetails> getAllForClient(long clientId) {
    cachedReservations = reservationDao.getAllWithDetailsByClientId(clientId);
    return cachedReservations;
  }

  public ReservationWithDetails getById(long reservationId) {
    var reservation =
        cachedReservations != null
            ? findFromCache(reservationId)
            : reservationDao.getWithDetailsById(reservationId);

    return reservation.orElseGet(null);
  }

  public void cancel(long reservationId) {
    reservationDao.cancel(reservationId);

    if (cachedReservations != null) {
      findFromCache(reservationId)
          .ifPresent(
              reservation -> reservation.reservation().setStatus(ReservationStatus.CANCELLED));
    }
  }

  private Optional<ReservationWithDetails> findFromCache(long id) {
    return cachedReservations.stream()
        .filter(reservations -> reservations.reservation().getId() == id)
        .findAny();
  }
}

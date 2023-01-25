package com.kgs.RoomReservationApp.service;

import static com.kgs.RoomReservationApp.utils.TestDataProvider.getReservationWithDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kgs.RoomReservationApp.dao.ReservationDao;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReservationServiceTest {

  static ReservationDao dao = mock(ReservationDao.class);
  ReservationService reservationService = new ReservationService(dao);

  @BeforeAll
  static void setupDao() {
    var reservation = getReservationWithDetails();

    when(dao.getWithDetailsById(1)).thenReturn(Optional.of(reservation));
    when(dao.getAllWithDetailsByClientId(1)).thenReturn(List.of(reservation));
  }

  @Test
  void shouldGetAllReservationsForClient() {
    int clientId = 1;
    var actual = reservationService.getAllForClient(clientId);
    var expected = List.of(getReservationWithDetails());

    assertEquals(expected, actual);
  }

  @Test
  void getById() {}

  @Test
  void cancel() {}
}

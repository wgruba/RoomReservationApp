package com.kgs.RoomReservationApp.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.kgs.RoomReservationApp.model.Reservation;
import com.kgs.RoomReservationApp.model.ReservationStatus;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql({"/db-init.sql", "/reservation-dao-test-init-data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ReservationDaoTest {

  @Autowired JdbcTemplate jdbcTemplate;
  ReservationDao reservationDao;

  @BeforeEach
  void setupReservationTable() {
    reservationDao = new ReservationDao(jdbcTemplate);
  }

  @Test
  void shouldGetAllReservationsWithDetailsByClientId() {
    int clientId = 1;
    var expected =
        List.of(
            new ReservationWithDetails(
                new Reservation(
                    1,
                    1,
                    Instant.EPOCH,
                    Instant.EPOCH.plus(Duration.ofDays(2)),
                    ReservationStatus.ACTIVE,
                    "comments"),
                1,
                100.0,
                "Regular"));
    var actual = reservationDao.getAllWithDetailsByClientId(clientId);

    assertEquals(expected, actual);
  }

  @Test
  void shouldGetReservationWithDetailsById() {
    int reservationId = 1;
    var expected =
        new ReservationWithDetails(
            new Reservation(
                1,
                1,
                Instant.EPOCH,
                Instant.EPOCH.plus(Duration.ofDays(2)),
                ReservationStatus.ACTIVE,
                "comments"),
            1,
            100.0,
            "Regular");
    var actual = reservationDao.getWithDetailsById(reservationId);

    assertEquals(expected, actual.orElse(null));
  }

  @Test
  void shouldCancelReservation() {
    int reservationId = 1;
    reservationDao.cancel(reservationId);

    var actualStatus =
        reservationDao.getWithDetailsById(reservationId).get().reservation().getStatus();

    assertEquals(ReservationStatus.CANCELLED, actualStatus);
  }
}

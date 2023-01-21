package com.kgs.RoomReservationApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kgs.RoomReservationApp.Dao.RoomDao;
import com.kgs.RoomReservationApp.Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql("/db-init.sql")
public class DatabaseIntegrationTest {

  @Autowired JdbcTemplate jdbcTemplate;
  RoomDao roomDao;

  @BeforeEach
  void setup() {
    roomDao = new RoomDao(jdbcTemplate);
  }

  @Test
  void shouldGetRoom() {
    var rooms = roomDao.getAll();
    var expected = new Room(1L, "Single", 1L, 1, 100.0, "Regular single room");

    assertEquals(expected, rooms.get(0));
  }
}

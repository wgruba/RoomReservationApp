package com.kgs.RoomReservationApp.Dao;

import com.kgs.RoomReservationApp.Model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomDao {

  private final JdbcTemplate jdbcTemplate;

  public List<Room> getAll() {
    return jdbcTemplate.query(
        "SELECT r.*, rt.type_name FROM rooms r JOIN room_types rt ON r.id = rt.id",
        this::mapToRoom);
  }

  private Room mapToRoom(ResultSet rs, int rowNum) throws SQLException {
    return new Room(
        rs.getLong("r.id"),
        rs.getString("rt.room_type"),
        rs.getLong("r.hotel_id"),
        rs.getInt("r.max_people_number"),
        rs.getFloat("r.daily_price"),
        rs.getString("r.description"));
  }
}

package com.kgs.RoomReservationApp.Dao;

import com.kgs.RoomReservationApp.Model.Room;
import com.kgs.RoomReservationApp.Model.RoomType;
import jakarta.annotation.PostConstruct;
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
    return jdbcTemplate.query("SELECT * FROM rooms", this::mapToRoom);
  }

  private Room mapToRoom(ResultSet rs, int rowNum) throws SQLException {
    return new Room(
        rs.getLong("id"),
        RoomType.getRoomTypeForNumber(rs.getInt("room_type")),
        rs.getLong("hotel_id"),
        rs.getInt("max_people_number"),
        rs.getFloat("daily_price"),
        rs.getString("description"));
  }
}

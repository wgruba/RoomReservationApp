package com.kgs.RoomReservationApp.dao;

import com.kgs.RoomReservationApp.model.Client;
import com.kgs.RoomReservationApp.model.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

  public  Optional<Room> getRoomForReservation(long reservationId){
      return Optional.ofNullable(
              jdbcTemplate.queryForObject("""
            SELECT r.*, rt.type_name
            FROM rooms r 
            LeFT JOIN room_types rt ON r.id = rt.id
            JOIN room_reservation rr ON r.id = rr.room_id
            WHERE rr.reservation_id = ?
            """,this::mapToRoom,reservationId));
  }
  public Optional<Integer>  updateReservedRoom(Room room, long reservationId){
    return  Optional.ofNullable(
            jdbcTemplate.update("""
            UPDATE room_reservation
            SET room_id = ?
            WHERE reservation_id = ?
            """,room.getId(),reservationId));

  }


  private Room mapToRoom(ResultSet rs, int rowNum) throws SQLException {
    return new Room(
        rs.getLong("r.id"),
        rs.getString("r.room_type"),
        rs.getLong("r.hotel_id"),
        rs.getInt("r.max_people_number"),
        rs.getFloat("r.daily_price"),
        rs.getString("r.description"));
  }
}

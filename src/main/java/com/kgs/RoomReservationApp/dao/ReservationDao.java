package com.kgs.RoomReservationApp.dao;

import com.kgs.RoomReservationApp.model.Reservation;
import com.kgs.RoomReservationApp.model.ReservationStatus;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationDao {

  private final JdbcTemplate jdbcTemplate;

  public List<ReservationWithDetails> getAllWithDetailsByClientId(long userId) {
    return jdbcTemplate.query(
        """
          SELECT r.*, r2.max_people_number, r2.daily_price, rt.type_name FROM reservations r
          JOIN room_reservation rr ON r.id = rr.reservation_id
          JOIN rooms r2 ON r.id = r2.id
          JOIN room_types rt ON r2.id = rt.id
          WHERE r.id = ?
        """,
        this::mapToReservationWithDetails,
        userId);
  }

  private Reservation mapToReservation(ResultSet rs, int rowNum) throws SQLException {
    return new Reservation(
        rs.getLong("id"),
        rs.getLong("user_id"),
        rs.getTimestamp("start").toInstant(),
        rs.getTimestamp("end").toInstant(),
        ReservationStatus.getReservationStatusForNumber(rs.getInt("status")),
        rs.getString("comments"));
  }

  private ReservationWithDetails mapToReservationWithDetails(ResultSet rs, int rowNum)
      throws SQLException {
    return new ReservationWithDetails(
        mapToReservation(rs, rowNum),
        rs.getInt("max_people_number"),
        rs.getFloat("daily_price"),
        rs.getString("room_type"));
  }
}

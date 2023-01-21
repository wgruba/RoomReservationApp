package com.kgs.RoomReservationApp.dao;

import com.kgs.RoomReservationApp.model.Reservation;
import com.kgs.RoomReservationApp.model.ReservationStatus;
import com.kgs.RoomReservationApp.model.ReservationWithDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
              SELECT res.*, rooms.max_people_number, rooms.daily_price, types.type_name FROM reservations res
              JOIN room_reservation rr ON res.id = rr.reservation_id
              JOIN rooms ON rr.room_id  = rooms.id
              JOIN room_types types ON rooms.room_type_id = types.id
              WHERE res.user_id = ?
              ORDER BY res.start DESC
        """,
        this::mapToReservationWithDetails,
        userId);
  }

  public Optional<ReservationWithDetails> getWithDetailsById(long reservationId) {
    return Optional.ofNullable(
        jdbcTemplate.queryForObject(
            """
                  SELECT res.*, rooms.max_people_number, rooms.daily_price, types.type_name FROM reservations res
                  JOIN room_reservation rr ON res.id = rr.reservation_id
                  JOIN rooms ON rr.room_id  = rooms.id
                  JOIN room_types types ON rooms.room_type_id = types.id
                  WHERE res.id = ?
                  ORDER BY res.start DESC
            """,
            this::mapToReservationWithDetails,
            reservationId));
  }

  public void cancel(long reservationId) {
    jdbcTemplate.update("UPDATE reservations SET status = 3 WHERE id = ?", reservationId);
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
        rs.getString("type_name"));
  }
}

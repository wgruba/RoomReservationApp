package com.kgs.RoomReservationApp.dao;

import com.kgs.RoomReservationApp.model.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientDao {

  private final JdbcTemplate jdbcTemplate;

  public Optional<Client> getByMail(String email) {
    return Optional.ofNullable(
        jdbcTemplate.queryForObject(
            "SELECT * FROM clients WHERE email = ?", this::mapToClient, email));
  }

  public Optional<Client> getClientByReservationId(long reservationId){
    return Optional.ofNullable(
            jdbcTemplate.queryForObject("""
            SELECT clients.*
            FROM clients
            JOIN reservations ON clients.id = reservations.user_id
            WHERE reservations.id = ?
            """,this::mapToClient,reservationId));
  }

  private Client mapToClient(ResultSet rs, int rowNum) throws SQLException {
    return new Client(
        rs.getLong("id"),
        rs.getString("email"),
        rs.getString("fist_name"),
        rs.getString("last_name"),
        rs.getString("password"),
        rs.getString("phone_number"));
  }
}

package com.kgs.RoomReservationApp.Dao;

import com.kgs.RoomReservationApp.Model.Client;
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

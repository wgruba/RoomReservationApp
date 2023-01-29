package com.kgs.RoomReservationApp.dao;

import com.kgs.RoomReservationApp.model.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
  public Optional<Integer> updateClient(Client client){
    return  Optional.ofNullable(
            jdbcTemplate.update("""
            UPDATE clients
            SET fist_name= ?, last_name = ?,phone_number = ?,email = ?
            WHERE id = ?
            """,client.getFistName(),client.getLastName(),client.getPhoneNumber(),client.getEmail(),client.getId()));
  }

  public Client getClientByReservationId(long reservationId){
    return
            jdbcTemplate.queryForObject("""
            SELECT clients.*
            FROM clients
            JOIN reservations ON clients.id = reservations.user_id
            WHERE reservations.id = ?
            """,this::mapToClient,reservationId);
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

package com.kgs.RoomReservationApp.dao;


import com.kgs.RoomReservationApp.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Employee> getByEmployeeId(String employeeId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT * FROM employees WHERE employee_id = ?", this::mapToEmployee, employeeId));
    }

    private Employee mapToEmployee(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                .id(rs.getLong("id"))
                .employeeId(rs.getString("employee_id"))
                .password(rs.getString("password"))
                .isActive(mapActive(rs.getInt("active")))
                .build();
    }

    private boolean mapActive(int isActive){
        switch (isActive){
            case 1:
                return true;
            default:
                return false;
        }
    }
}

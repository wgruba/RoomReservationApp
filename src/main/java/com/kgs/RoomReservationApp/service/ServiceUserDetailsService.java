package com.kgs.RoomReservationApp.service;

import com.kgs.RoomReservationApp.dao.ClientDao;
import com.kgs.RoomReservationApp.dao.EmployeeDao;
import com.kgs.RoomReservationApp.model.Client;
import com.kgs.RoomReservationApp.model.Employee;
import com.kgs.RoomReservationApp.model.ServiceUserDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUserDetailsService implements UserDetailsService {

  private final ClientDao clientDao;
  private final EmployeeDao employeeDao;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    if (userName.contains("Employee")){
      Optional<Employee> user = employeeDao.getByEmployeeId(userName);
      if (user.isEmpty()) throw new UsernameNotFoundException("Not found : " + userName);

      return new ServiceUserDetails(user.get());
    }
    else {
      Optional<Client> user = clientDao.getByMail(userName);
      if (user.isEmpty()) throw new UsernameNotFoundException("Not found : " + userName);

      return new ServiceUserDetails(user.get());
    }
  }
}

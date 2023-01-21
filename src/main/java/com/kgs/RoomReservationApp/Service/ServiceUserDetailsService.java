package com.kgs.RoomReservationApp.Service;

import com.kgs.RoomReservationApp.Dao.ClientDao;
import com.kgs.RoomReservationApp.Model.Client;
import com.kgs.RoomReservationApp.Model.ServiceUserDetails;
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

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    Optional<Client> user = clientDao.getByMail(mail);

    if (user.isEmpty()) throw new UsernameNotFoundException("Not found : " + mail);

    return new ServiceUserDetails(user.get());
  }
}

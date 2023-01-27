package com.kgs.RoomReservationApp.service;


import com.kgs.RoomReservationApp.dao.ClientDao;
import com.kgs.RoomReservationApp.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientDao clientDao;

    public Optional<Client> findClientAssignToReservation(long reservationId){
        return clientDao.getClientByReservationId(reservationId);
    }
}

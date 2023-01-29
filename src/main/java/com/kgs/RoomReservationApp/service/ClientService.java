package com.kgs.RoomReservationApp.service;


import com.kgs.RoomReservationApp.dao.ClientDao;
import com.kgs.RoomReservationApp.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientDao clientDao;

    public Client findClientAssignToReservation(long reservationId){
        return clientDao.getClientByReservationId(reservationId);
    }
    public void updateClient(Client client){
        clientDao.updateClient(client);
    }
}

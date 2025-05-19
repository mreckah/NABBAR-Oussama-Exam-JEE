package com.exam.service;

import com.exam.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    void delete(Long id);
}

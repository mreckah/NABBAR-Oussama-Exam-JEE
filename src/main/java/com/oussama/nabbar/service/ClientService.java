package com.oussama.nabbar.service;

import com.oussama.nabbar.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO saveClient(ClientDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    void deleteClient(Long id);
}

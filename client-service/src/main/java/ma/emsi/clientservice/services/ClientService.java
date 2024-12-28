package ma.emsi.clientservice.services;

import ma.emsi.clientservice.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);
    ClientDto getClientById(Long id);
    ClientDto updateClient(ClientDto clientDto);
    Boolean deleteClient(Long id);
    List<ClientDto> getListClientDto();
}

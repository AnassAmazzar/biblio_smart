package ma.emsi.clientservice.services;

import ma.emsi.clientservice.dao.entities.Client;
import ma.emsi.clientservice.dao.repository.ClientRepository;
import ma.emsi.clientservice.dto.ClientDto;
import ma.emsi.clientservice.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientServiceManager implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        System.out.println(clientDto);
        ClientDto cltDto = clientMapper.fromClientToClientDto(clientRepository.save(clientMapper.fromClientDtoToClient(clientDto)));
        System.out.println(cltDto);
        return cltDto;
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id).get();
        ClientDto clientDto = clientMapper.fromClientToClientDto(client);
        return clientDto;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        ClientDto clDto = null;
        Client client = clientRepository.findById(clientDto.getId()).get();
        if(client!=null){
            clDto = clientMapper.fromClientToClientDto(clientRepository.save(
                    clientMapper.fromClientDtoToClient(clientDto)));
        }
        return clDto;
    }

    @Override
    public Boolean deleteClient(Long id) {
        boolean status=false;
        if(id!=null){
            clientRepository.deleteById(id);
            status=true;
        }
        return status;
    }

    @Override
    public List<ClientDto> getListClientDto() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        for(Client client : clientRepository.findAll()){
            clientDtoList.add(clientMapper.fromClientToClientDto(client));
        }
        return clientDtoList;
    }
}

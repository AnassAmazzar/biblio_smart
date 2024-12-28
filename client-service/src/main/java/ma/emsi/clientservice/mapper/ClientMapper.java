package ma.emsi.clientservice.mapper;

import ma.emsi.clientservice.dao.entities.Client;
import ma.emsi.clientservice.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    ModelMapper mapper = new ModelMapper();

    //fromClientToClientDto
    public ClientDto fromClientToClientDto(Client client){
        return this.mapper.map(client, ClientDto.class);
    }

    //fromClientDtoToClient
    public Client fromClientDtoToClient(ClientDto clientDto){
        return this.mapper.map(clientDto, Client.class);
    }
}

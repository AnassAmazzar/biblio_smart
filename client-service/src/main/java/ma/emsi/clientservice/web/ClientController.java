package ma.emsi.clientservice.web;

import ma.emsi.clientservice.dto.ClientDto;
import ma.emsi.clientservice.services.ClientServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    ClientServiceManager clientServiceManager;

    @MutationMapping
    public ClientDto addClient(@Argument ClientDto clientDto){
        return clientServiceManager.addClient(clientDto);
    }

    @MutationMapping
    public ClientDto updateClient(@Argument ClientDto clientDto){
        return clientServiceManager.updateClient(clientDto);
    }

    @MutationMapping
    public Boolean deleteClientById(@Argument Long id){
        return clientServiceManager.deleteClient(id);
    }


    @QueryMapping
    public ClientDto getClientById(@Argument Long id){
        System.out.println("Query for geting client id : " + id);
        return clientServiceManager.getClientById(id);
    }

    @QueryMapping
    public List<ClientDto> getListClient() {
        return clientServiceManager.getListClientDto();
    }
}

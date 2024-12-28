package ma.emsi.salesmanagementservice.web;

import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VenteController {

    @Autowired
    VenteService venteService;

    @MutationMapping
    public VenteDto addProduit(@Argument VenteDto venteDto){
        return venteService.addVente(venteDto);
    }

    @QueryMapping
    public VenteDto getVenteById(@Argument Long id){
        return venteService.getVenteById(id);
    }
}

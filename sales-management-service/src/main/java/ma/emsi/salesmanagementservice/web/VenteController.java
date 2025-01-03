package ma.emsi.salesmanagementservice.web;

import lombok.extern.java.Log;
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
    public VenteDto addVente(@Argument VenteDto venteDto){
        //System.out.println("teste controller addProduit");
        return venteService.addVente(venteDto);
    }


    /*
    @MutationMapping
    public VenteDto deleteVente(@Argument Long idClient,@Argument Long idProduit){
        return venteService.deleteVente(idClient,idProduit);
    }
    */


   @QueryMapping
    public VenteDto getVenteById(@Argument Long idClient,@Argument Long idProduit){
        return venteService.getVenteById(idClient,idProduit);
    }
}

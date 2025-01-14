package ma.emsi.salesmanagementservice.web;

import lombok.extern.java.Log;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VenteController {

    @Autowired
    VenteService venteService;

    @MutationMapping
    public VenteDto addVente(@Argument VenteDto venteDto){return venteService.addVente(venteDto);}

    @QueryMapping
    public VenteDto getVenteById(@Argument Long idClient,@Argument Long idProduit){
        System.out.println("teste" + idProduit);
        return venteService.getVenteById(idClient,idProduit);
    }

    @MutationMapping
    public boolean deleteVenteById(@Argument Long id){
        return venteService.deleteVente(id);
    }

    @MutationMapping
    public VenteDto updateVente(@Argument VenteDto venteDto){
        return venteService.updateVente(venteDto);
    }

    @MutationMapping
    public VenteDto updateQuantite(@Argument VenteDto venteDto){
        return venteService.updateVente(venteDto);
    }
    @QueryMapping
    public List<VenteDto> getListVente(){
        return venteService.getListVente();
    }
}

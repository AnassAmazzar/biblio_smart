package ma.emsi.salesmanagementservice.web;

import com.stripe.exception.StripeException;
import lombok.extern.java.Log;
import ma.emsi.salesmanagementservice.dao.entities.Commande;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.services.StripeService;
import ma.emsi.salesmanagementservice.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @MutationMapping
    public Commande validateVente(@Argument Long idClient){
        return venteService.validateVente(idClient);
    }

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestParam int amount) throws StripeException {
        return stripeService.createPaymentIntent(amount, "usd");
    }
}

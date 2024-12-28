package ma.emsi.productservice.web;


import ma.emsi.productservice.dto.ProduitDto;
import ma.emsi.productservice.services.ProduitService;
import ma.emsi.productservice.services.ProduitServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProduitController {
    @Autowired
    ProduitService produitService;

    @MutationMapping
    public ProduitDto addProduit(@Argument ProduitDto produitDto){
        return produitService.addproduit(produitDto);
    }

    @MutationMapping
    public ProduitDto updateProduct(@Argument ProduitDto produitDto) {
        return produitService.updateProduit(produitDto);
    }

    @MutationMapping
    public Boolean deleteProductById(@Argument Integer id){
        return produitService.deleteProduit(id);
    }

    @QueryMapping
    public ProduitDto getProductById(@Argument Integer id){
        return produitService.getProduitById(id);
    }

    @QueryMapping
    public List<ProduitDto> getAllProduct() {
        return produitService.getListProduitDto();
    }


}

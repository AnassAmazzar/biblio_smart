package ma.emsi.productservice.mapper;

import ma.emsi.productservice.dao.entities.Produit;
import ma.emsi.productservice.dto.ProduitDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {
    ModelMapper mapper = new ModelMapper();

    //fromProduitToProduitDto
    public ProduitDto fromProduitToProduitDto(Produit produit){
        return this.mapper.map(produit, ProduitDto.class);
    }

    //fromProduitDtoToDto
    public Produit fromProduitDtoToProduit(ProduitDto produitDto){
        return this.mapper.map(produitDto, Produit.class);
    }

}

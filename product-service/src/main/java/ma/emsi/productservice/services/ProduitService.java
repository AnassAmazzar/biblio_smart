package ma.emsi.productservice.services;


import ma.emsi.productservice.dto.ProduitDto;

import java.util.List;

public interface ProduitService {
    ProduitDto addproduit(ProduitDto produitDto);
    ProduitDto getProduitById(Integer id);
    ProduitDto updateProduit(ProduitDto produitDto);
    Boolean deleteProduit(Integer id);
    List<ProduitDto> getListProduitDto();
}

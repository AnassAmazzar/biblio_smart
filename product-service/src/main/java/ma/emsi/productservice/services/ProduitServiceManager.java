package ma.emsi.productservice.services;

import ma.emsi.productservice.dao.entities.Produit;
import ma.emsi.productservice.dao.repository.ProduitRepository;
import ma.emsi.productservice.dto.ProduitDto;
import ma.emsi.productservice.mapper.ProduitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProduitServiceManager implements ProduitService{

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ProduitMapper produitMapper;

    @Override
    public ProduitDto addproduit(ProduitDto produitDto) {
        ProduitDto prodDto = produitMapper.fromProduitToProduitDto(produitRepository.save(produitMapper.fromProduitDtoToProduit(produitDto)));
        return prodDto;
    }

    @Override
    public ProduitDto getProduitById(Integer id) {
        Produit produit = produitRepository.findById(id).get();
        ProduitDto produitDto = produitMapper.fromProduitToProduitDto(produit);
        return produitDto;
    }

    @Override
    public ProduitDto updateProduit(ProduitDto produitDto) {
        ProduitDto produitDto1 = null;
        Produit produit = produitRepository.findById(produitDto.getId()).get();
        if(produit!=null){
            produitDto1 = produitMapper.fromProduitToProduitDto(produitRepository.save(produitMapper.fromProduitDtoToProduit(produitDto)));
        }
        return produitDto1;
    }

    @Override
    public Boolean deleteProduit(Integer id) {
        boolean status=false;
        if(id!=null){
            status=true;
            produitRepository.deleteById(id);
        }
        return status;
    }

    @Override
    public List<ProduitDto> getListProduitDto() {
        List<ProduitDto> produitDtoList = new ArrayList<ProduitDto>();
        for(Produit produit : produitRepository.findAll()){
            produitDtoList.add(produitMapper.fromProduitToProduitDto(produit));
        }
        return produitDtoList;
    }
}

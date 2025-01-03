package ma.emsi.salesmanagementservice.services;


import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dao.repository.VenteRepository;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
import ma.emsi.salesmanagementservice.feign.ProductServiceFeign;
import ma.emsi.salesmanagementservice.mapper.VenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class VenteServiceManager implements VenteService{
    @Autowired
    VenteRepository venteRepository;

    @Autowired
    ClientServiceFeign clientServiceFeign;

    @Autowired
    ProductServiceFeign productServiceFeign;

    @Autowired
    VenteMapper venteMapper;

    @Override
    public boolean checkClientId(Long idClient, Long idClientVente){
        System.out.println("teste checkClientId ");
        return Objects.equals(idClient, idClientVente);
    }
    @Override
    public boolean checkProductId(Long idProduit, Long idProduitVente){
        System.out.println("teste checkProductId ");
        return Objects.equals(idProduit, idProduitVente);
    }

    @Override
    public VenteDto addVente(VenteDto venteDto) {
        System.out.println(clientServiceFeign.getClientById(venteDto.getIdClient()));
        if(checkClientId(clientServiceFeign.getClientById(venteDto.getIdClient()),venteDto.getIdClient())&&checkProductId(productServiceFeign.getProductById(venteDto.getIdProduit()),venteDto.getIdProduit())){
            VenteDto vteDto = venteMapper.fromVenteToVenteDto(venteRepository.save(venteMapper.fromVenteDtoToVente(venteDto)));
            return vteDto;
        }else {
           System.out.println("client Id ou Product Id introuvable");
        }
        return venteDto;

    }

    @Override
    public VenteDto getVenteById(Long productId, Long clientId) {
        VenteDto venteDto = venteMapper.fromVenteToVenteDto(venteRepository.findByIdClientAndIdProduit(productId,clientId));
        return venteDto;
    }

    @Override
    public VenteDto updateVente(VenteDto produitDto) {
        return null;
    }



    @Override
    public VenteDto deleteVente(Long idClient, Long idProduit) {
        VenteDto venteDto = venteMapper.fromVenteToVenteDto(venteRepository.deleteVenteByIdClientAndIdProduit(idClient,idProduit));
        return venteDto;
    }


    @Override
    public List<VenteDto> getListVenteDto() {
        List<VenteDto> venteDtoList = new ArrayList<VenteDto>();
        for(Vente vente : venteRepository.findAll()){
            venteDtoList.add(venteMapper.fromVenteToVenteDto(vente));
        }
        return venteDtoList;
    }
}

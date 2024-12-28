package ma.emsi.salesmanagementservice.services;


import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dao.repository.VenteRepository;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
import ma.emsi.salesmanagementservice.feign.ProductServiceFeign;
import ma.emsi.salesmanagementservice.graph.GraphQLQueryService;
import ma.emsi.salesmanagementservice.mapper.VenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public VenteDto addVente(VenteDto venteDto) {
        if(venteDto.getIdClient().equals(clientServiceFeign.getClientById(venteDto.getIdClient()))&&venteDto.getProduitId().equals(productServiceFeign.getProductById(venteDto.getProduitId()))){
            VenteDto vteDto = venteMapper.fromVenteToVenteDto(venteRepository.save(venteMapper.fromVenteDtoToVente(venteDto)));
            return vteDto;
        }else {
           System.out.println("client Id ou Product Id introuvable");
        }
        return venteDto;

    }

    @Override
    public VenteDto getVenteById(Long id) {
        VenteDto venteDto = venteMapper.fromVenteToVenteDto(venteRepository.findById(id).get());
        return venteDto;
    }

    @Override
    public VenteDto updateVente(VenteDto produitDto) {
        return null;
    }

    @Override
    public VenteDto deleteVente(Long id) {
        venteRepository.deleteById(id);
        return null;
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

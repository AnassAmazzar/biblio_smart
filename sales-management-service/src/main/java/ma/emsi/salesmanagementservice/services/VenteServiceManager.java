package ma.emsi.salesmanagementservice.services;


import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dao.repository.VenteRepository;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
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
    GraphQLQueryService graphQLQueryService;

    @Autowired
    VenteMapper venteMapper;

    @Override
    public VenteDto addVente(VenteDto venteDto) {
        VenteDto vtDto = null;
        // Préparer les variables
        String queryClient =
                """
                query($id: Int){
                    getClientById(id: $id){
                        id
                        email
                        nom
                    }
                }""";
        String queryProd =
                """
                query($id: Int){
                    getProductById(id: $id){
                        id
                        marque
                        qteStock
                    }
                }""";
        //Map<String, Object> variables = Map.of("id", venteDto.getIdClient());

        // Construire le corps de la requête
        //Map<String, Object> request = Map.of(
                //"query", query,
                //"variables", variables
        //);
        //GraphQLQuery graphQLQuery = new GraphQLQuery(query);
        //Map<String, Object> response = graphQLQueryService.getClientById(request);
        //Map<String, Object> data = (Map<String, Object>) response.get("data");
        //Map<String, Object> getClientById = (Map<String, Object>) data.get("getClientById");
        System.out.println("Teste ID Prod 1 : " + venteDto.getProduitId());
        System.out.println("Teste ID client 1 : " + venteDto.getIdClient());
        if(graphQLQueryService.getSClientId(queryClient,venteDto.getIdClient())!=null
                && graphQLQueryService.getSProduitId(queryProd,venteDto.getProduitId())!=null){
            vtDto = venteMapper.fromVenteToVenteDto(
                    venteRepository.save(venteMapper.fromVenteDtoToVente(venteDto)));
        }
        return vtDto;
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

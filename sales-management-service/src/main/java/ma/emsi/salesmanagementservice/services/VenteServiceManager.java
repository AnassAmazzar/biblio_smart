package ma.emsi.salesmanagementservice.services;


import ma.emsi.salesmanagementservice.dao.entities.Commande;
import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dao.repository.VenteRepository;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
import ma.emsi.salesmanagementservice.graph.GraphQLQueryService;
import ma.emsi.salesmanagementservice.mapper.VenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class VenteServiceManager implements VenteService{
    @Autowired
    VenteRepository venteRepository;

    @Autowired
    GraphQLQueryService graphQLQueryService;

    @Autowired
    VenteMapper venteMapper;




        private String queryQuantite =
                """
                 query($id: Int){
                     getProductById(id: $id){
                         qteStock
                     }
                 }""";



    private String queryClient =
            """
            query($id: Int){
                getClientById(id: $id){
                    id
                    email
                    nom
                }
            }""";
    private String queryProd =
            """
            query($id: Int){
                getProductById(id: $id){
                    id
                    marque
                    qteStock
                }
            }""";


    @Override
    public VenteDto addVente(VenteDto venteDto) {
        VenteDto vtDto = null;
        // Préparer les variables
        if(venteDto.getQuantitie()>(Integer) graphQLQueryService.getQentiter(queryQuantite, venteDto.getId())){
            throw new IllegalArgumentException("Quantite en stock insuffisante");

        }
        if(graphQLQueryService.getSClientId(queryClient,venteDto.getIdClient())!=null
                && graphQLQueryService.getSProduitId(queryProd,venteDto.getProduitId())!=null){
            vtDto = venteMapper.fromVenteToVenteDto(
                    venteRepository.save(venteMapper.fromVenteDtoToVente(venteDto)));
        }
        return vtDto;
    }

    @Override
    public VenteDto getVenteById(Long idClient, Long idProduit) {
        VenteDto venteDto = null ;
        try {
            String clientId = (String)graphQLQueryService.getSClientId(queryClient,idClient);
            String produitId = (String)graphQLQueryService.getSProduitId(queryProd,idProduit);
            if(clientId!=null &&  produitId!=null){
                venteDto = venteMapper.fromVenteToVenteDto(venteRepository.
                        findVenteByIdClientAndProduitId(Long.parseLong(clientId),Long.parseLong(produitId))
                );
            }
        }catch (Exception e){
            System.out.println("Vente not found " + e.toString());
        }
        return venteDto;
    }

    @Override
    public VenteDto updateVente(VenteDto venteDto) {

          VenteDto venteDto1 = null;
          /*
            if (venteDto.getId() == null) {
            throw new IllegalArgumentException("ID de la vente ne peut pas être null pour une mise à jour.");
        }

          */

        Vente vente = venteRepository.findById(venteDto.getId()).get();
        System.out.println("Vente ID"+vente.getId());
        if(vente!=null){
            venteDto1 = venteMapper.fromVenteToVenteDto(venteRepository.save(venteMapper.fromVenteDtoToVente(venteDto)));
        }
        return venteDto1;

    }

    @Override
    public boolean deleteVente(Long id) {

        boolean status=false;
        if(id!=null){
            status=true;
            venteRepository.deleteById(id);
        }
        return status;

    }

    @Override
    public List<VenteDto> getListVente() {
        List<VenteDto> venteDtoList = new ArrayList<>();
        for(Vente vente : venteRepository.findAll()){
            venteDtoList.add(venteMapper.fromVenteToVenteDto(vente));
        }
        return venteDtoList;
    }

    @Override
    public Commande validateVente(Long idClient) {

        List<Vente> ventes = venteRepository.findVenteByIdClient(idClient);

        if (ventes.isEmpty()) {
            throw new IllegalArgumentException("No sales found for this customer");
        }

        // Step 2: Create a new Commande
        Commande commande = new Commande();
        commande.setCustomerId(idClient);
        commande.setSales(ventes);
        commande.setValidationDate(LocalDateTime.now());
        commande.setStatus("VALIDATED");

        // Step 3: Communicate with ProductService to update quantities
        /*
        ventes.forEach(sale -> {
            productFeignClient.updateProductQuantity(sale.getProductId(), sale.getQuantity());
        });

         */

        return null;
    }
}

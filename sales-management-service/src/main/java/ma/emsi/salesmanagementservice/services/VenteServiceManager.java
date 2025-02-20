package ma.emsi.salesmanagementservice.services;


import ma.emsi.salesmanagementservice.dao.entities.Commande;
import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dao.repository.CommandeRepository;
import ma.emsi.salesmanagementservice.dao.repository.VenteRepository;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import ma.emsi.salesmanagementservice.feign.ClientServiceFeign;
import ma.emsi.salesmanagementservice.graph.GraphQLQueryService;
import ma.emsi.salesmanagementservice.mapper.VenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class VenteServiceManager implements VenteService{
    @Autowired
    VenteRepository venteRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    GraphQLQueryService graphQLQueryService;

    @Autowired
    VenteMapper venteMapper;




        private String mutationQuantite =
            """
            mutation($id: Int, $quantite: Int) {
              updateProductByQuantity(id: $id, quantite: $quantite) {
                id
                marque
                nom
                prix
                qteStock
              }
            }""";

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
        System.out.println("Quantite"+venteDto.getQuantitie());
        System.out.println("Id Vente ADD"+venteDto.getProduitId());
        // Préparer les variables
        if(venteDto.getQuantitie()>(Integer) graphQLQueryService.getQentiter(queryQuantite, venteDto.getProduitId())){
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

        boolean valid=false;

        List<Vente> ventes = venteRepository.findVenteByIdClientAndValid(idClient,valid);

        if (ventes.isEmpty()) {
            throw new IllegalArgumentException("No sales found for this customer");
        }

        // Step 2: Create a new Commande


        List<Commande> existingCommandes = commandeRepository.findByCustomerId(idClient);
        System.out.println("existing commands : "+existingCommandes);
        existingCommandes.forEach(commande -> {
            System.out.println("Commande ID: " + commande.getId() + ", Sales: " + commande.getSales());
        });

        //System.out.println(" existingCommandes an : " + existingCommandes);

        Set<Long> existingSaleIds = existingCommandes.stream()
                .flatMap(c -> c.getSales().stream())
                .map(Vente::getId)
                .collect(Collectors.toSet());

        System.out.println("Existing Sale IDs: " + existingSaleIds);



// Filter out sales that already exist
        /*
          List<Vente> newSales = ventes.stream()
                .filter(vente -> !existingSaleIds.contains(vente.getId()))
                .collect(Collectors.toList());

// If there are no new sales, you might want to skip saving the Commande
        if (newSales.isEmpty()) {
            throw new IllegalArgumentException("No new sales to add for this customer");
        }
        */



        //System.out.println("New Sales: " + newSales);

// Add only the new sales
        Commande commande = new Commande();
        commande.setCustomerId(idClient);
        commande.setSales(ventes);
        commande.setValidationDate(LocalDateTime.now());
        commande.setStatus("VALIDATED");

        System.out.println("Sales being saved with Commande: " + commande.getSales());


        ventes.forEach(sale -> {
            Map<String, Object> variables = new HashMap<>();
            variables.put("id", sale.getProduitId());
            variables.put("quantite", sale.getQuantitie());


            try {
                Object response = graphQLQueryService.executeMutation(mutationQuantite, variables);
                // Optionally process the response to handle errors or log success
                System.out.println("Mutation response: " + response);
                sale.setValid(true);
                venteRepository.save(sale);
            } catch (Exception e) {
                throw new RuntimeException("Failed to update product quantity for product ID: " + sale.getProduitId(), e);
            }
        });

        commande = commandeRepository.save(commande);


        // Step 5: Return the created Commande
        return commande;
    }



   /*
    @Override
    public Commande validateVente(Long idClient) {
        List<Commande> existingCommandes = commandeRepository.findByCustomerId(idClient);

        System.out.println("Existing Commandes: " + existingCommandes);

        Set<Long> existingSaleIds = existingCommandes.stream()
                .flatMap(commande -> commande.getSales().stream())
                .map(Vente::getId)
                .collect(Collectors.toSet());

        System.out.println("Existing Sale IDs: " + existingSaleIds);

        List<Vente> ventes = venteRepository.findVenteByIdClient(idClient);

        List<Vente> newSales = ventes.stream()
                .filter(sale -> !existingSaleIds.contains(sale.getId()))
                .collect(Collectors.toList());

        if (newSales.isEmpty()) {
            throw new IllegalArgumentException("No new sales to add for this customer");
        }

        Commande commande = new Commande();
        commande.setCustomerId(idClient);
        commande.setValidationDate(LocalDateTime.now());
        commande.setStatus("VALIDATED");

        commande.setSales(newSales);
        newSales.forEach(sale -> sale.setCommande(commande));

        Commande savedCommande = commandeRepository.save(commande);
        System.out.println("Saved Commande: " + savedCommande);

        return savedCommande;
    }

   */

}

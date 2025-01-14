package ma.emsi.salesmanagementservice.services;

import ma.emsi.salesmanagementservice.dao.entities.Commande;
import ma.emsi.salesmanagementservice.dto.VenteDto;

import java.util.List;

public interface VenteService {
    VenteDto addVente(VenteDto venteDto);
    VenteDto getVenteById(Long idClient, Long produitId);
    VenteDto updateVente(VenteDto venteDto);
    boolean deleteVente(Long id);
    List<VenteDto> getListVente();
    Commande validateVente(Long idClient);

}

package ma.emsi.salesmanagementservice.services;

import ma.emsi.salesmanagementservice.dto.VenteDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VenteService {
    VenteDto addVente(VenteDto venteDto);
    VenteDto getVenteById(Long idClient, Long idProduit);
    VenteDto updateVente(VenteDto produitDto);
    VenteDto deleteVente(Long idClient, Long idProduit);
    List<VenteDto> getListVenteDto();
    boolean checkClientId(Long idClient, Long idClientVente);
    boolean checkProductId(Long idProduit, Long produitIdVente);
}

package ma.emsi.salesmanagementservice.services;

import ma.emsi.salesmanagementservice.dto.VenteDto;

import java.util.List;

public interface VenteService {
    VenteDto addVente(VenteDto venteDto);
    VenteDto getVenteById(Long clientId, Long produitId);
    VenteDto updateVente(VenteDto produitDto);
    VenteDto deleteVente(Long clienId, Long produitId);
    List<VenteDto> getListVenteDto();
}

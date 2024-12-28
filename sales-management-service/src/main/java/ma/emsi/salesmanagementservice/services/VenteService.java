package ma.emsi.salesmanagementservice.services;

import ma.emsi.salesmanagementservice.dto.VenteDto;

import java.util.List;

public interface VenteService {
    VenteDto addVente(VenteDto venteDto);
    VenteDto getVenteById(Long id);
    VenteDto updateVente(VenteDto produitDto);
    VenteDto deleteVente(Long id);
    List<VenteDto> getListVenteDto();
}

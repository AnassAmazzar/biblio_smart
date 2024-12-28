package ma.emsi.salesmanagementservice.mapper;


import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VenteMapper {
    ModelMapper mapper = new ModelMapper();


    //fromVenteToVenteDto
    public VenteDto fromVenteToVenteDto(Vente vente){
        return this.mapper.map(vente, VenteDto.class);
    }

    //fromVenteDtoToVente
    public Vente fromVenteDtoToVente(VenteDto venteDto){
        return this.mapper.map(venteDto, Vente.class);
    }

}

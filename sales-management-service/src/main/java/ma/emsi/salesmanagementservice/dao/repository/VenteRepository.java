package ma.emsi.salesmanagementservice.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.salesmanagementservice.dao.entities.Vente;
import ma.emsi.salesmanagementservice.dto.VenteDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface VenteRepository extends JpaRepository<Vente, Long> {

    Vente findVenteByIdClientAndProduitId(Long produitId, Long idClient);
    List<Vente> findVenteByIdClient(Long idClient);

    //Vente deleteVenteBy
}

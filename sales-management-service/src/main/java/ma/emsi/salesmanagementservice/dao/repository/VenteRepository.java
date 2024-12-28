package ma.emsi.salesmanagementservice.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.salesmanagementservice.dao.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface VenteRepository extends JpaRepository<Vente, Long> {

}

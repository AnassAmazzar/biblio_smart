package ma.emsi.salesmanagementservice.dao.repository;

import ma.emsi.salesmanagementservice.dao.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByCustomerId(Long customerId);
}

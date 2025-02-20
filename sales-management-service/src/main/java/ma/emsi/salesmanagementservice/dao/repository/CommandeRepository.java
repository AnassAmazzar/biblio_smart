package ma.emsi.salesmanagementservice.dao.repository;

import ma.emsi.salesmanagementservice.dao.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {

    @Query("SELECT c FROM Commande c LEFT JOIN FETCH c.sales WHERE c.customerId = :idClient")

    List<Commande> findByCustomerId(@Param("idClient") Long customerId);
}

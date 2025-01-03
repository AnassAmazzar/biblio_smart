package ma.emsi.salesmanagementservice.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.salesmanagementservice.dao.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository
@Transactional
public interface VenteRepository extends JpaRepository<Vente, Long> {

    //@Query("SELECT v FROM Vente v WHERE v.idProduit = :idProduit AND v.idClient = :idClient")
    //public Vente getVenteById();

    //@Transactional
    //@Modifying
    //@Query("DELETE FROM Vente v WHERE v.idProduit = :idProduit AND v.idClient = :idClient")
    //public Vente deleteVenteById(Long idProduit, Long idClient);
    Vente findByIdClientAndIdProduit(Long idProduit, Long idClient);

    Vente deleteVenteByIdClientAndIdProduit(Long idProduit, Long idClient);

}

package ma.emsi.productservice.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.productservice.dao.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}

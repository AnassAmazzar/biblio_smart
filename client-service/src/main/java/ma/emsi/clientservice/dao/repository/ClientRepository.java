package ma.emsi.clientservice.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.clientservice.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {
}

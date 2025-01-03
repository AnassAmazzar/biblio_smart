package ma.emsi.salesmanagementservice.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idClient;
    private Long produitId;
    private String email;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVente;
    private int quantitie;
}

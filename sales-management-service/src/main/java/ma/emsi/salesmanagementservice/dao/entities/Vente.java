package ma.emsi.salesmanagementservice.dao.entities;

import jakarta.persistence.*;
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
    private LocalDate dateVente;
    private int quantitie;
    private boolean valid;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
}

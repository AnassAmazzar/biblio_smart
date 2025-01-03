package ma.emsi.salesmanagementservice.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Vente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idClient;
    private Long idProduit;
    private LocalDate dateVente;
    private int quantitie;
}

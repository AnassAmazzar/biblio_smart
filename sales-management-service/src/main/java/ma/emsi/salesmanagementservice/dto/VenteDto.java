package ma.emsi.salesmanagementservice.dto;


import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VenteDto {
    private Long idClient;
    private Long idProduit;
    private LocalDate dateVente;
    private int quantitie;
}

package ma.emsi.salesmanagementservice.dto;


import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VenteDto {
    private Long idClient;
    private Long produitId;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVente;
    private int quantitie;
}

package ma.emsi.productservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProduitDto {
    private Integer id;
    private String nom;
    private String marque;
    private Double prix;
    private Integer qteStock;
}

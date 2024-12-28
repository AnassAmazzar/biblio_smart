package ma.emsi.salesmanagementservice.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Integer id;
    private String nom;
    private String marque;
    private Double prix;
    private Integer qteStock;
}

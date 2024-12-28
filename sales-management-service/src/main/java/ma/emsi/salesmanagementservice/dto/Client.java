package ma.emsi.salesmanagementservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String password;
}
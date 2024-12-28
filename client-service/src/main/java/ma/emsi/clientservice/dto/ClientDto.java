package ma.emsi.clientservice.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String password;
}

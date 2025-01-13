package ma.emsi.authservice.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import ma.emsi.authservice.dao.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

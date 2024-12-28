package ma.emsi.authservice.dto;


import lombok.*;

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
}

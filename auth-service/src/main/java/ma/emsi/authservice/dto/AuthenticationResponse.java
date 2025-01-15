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
public class AuthenticationResponse {
    private String token;
    @Enumerated(EnumType.STRING)
    private Role role;
}

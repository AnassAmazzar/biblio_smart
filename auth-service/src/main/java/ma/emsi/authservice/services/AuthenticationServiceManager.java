package ma.emsi.authservice.services;


import lombok.RequiredArgsConstructor;
import ma.emsi.authservice.config.JwtService;
import ma.emsi.authservice.dao.entities.Role;
import ma.emsi.authservice.dao.entities.User;
import ma.emsi.authservice.dao.repository.UserRepository;
import ma.emsi.authservice.dto.AuthenticationRequest;
import ma.emsi.authservice.dto.AuthenticationResponse;
import ma.emsi.authservice.dto.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceManager implements AuthenticationService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse inscription(RegisterRequest registerRequest) {
        var user = User.builder()
                .nom(registerRequest.getNom())
                .prenom(registerRequest.getPrenom())
                .telephone(registerRequest.getTelephone())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.User)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse connexion(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
}

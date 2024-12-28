package ma.emsi.authservice.services;

import ma.emsi.authservice.dao.entities.User;
import ma.emsi.authservice.dto.AuthenticationRequest;
import ma.emsi.authservice.dto.AuthenticationResponse;
import ma.emsi.authservice.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse inscription(RegisterRequest registerRequest);
    AuthenticationResponse connexion(AuthenticationRequest authenticationRequest);
}

package ma.emsi.authservice.web;


import lombok.RequiredArgsConstructor;
import ma.emsi.authservice.dto.AuthenticationRequest;
import ma.emsi.authservice.dto.AuthenticationResponse;
import ma.emsi.authservice.dto.RegisterRequest;
import ma.emsi.authservice.services.AuthenticationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @MutationMapping
    public AuthenticationResponse register(@Argument RegisterRequest registerRequest) {
        return authenticationService.inscription(registerRequest);
    }

    @QueryMapping
    public AuthenticationResponse login(@Argument AuthenticationRequest authenticationRequest){
        return authenticationService.connexion(authenticationRequest);
    }

}

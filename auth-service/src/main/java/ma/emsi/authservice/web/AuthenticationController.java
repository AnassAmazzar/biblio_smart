package ma.emsi.authservice.web;


import lombok.RequiredArgsConstructor;
import ma.emsi.authservice.dto.AuthenticationRequest;
import ma.emsi.authservice.dto.AuthenticationResponse;
import ma.emsi.authservice.dto.RegisterRequest;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return null;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        return null;
    }

}

package hotel.domain.controller;

import hotel.autentication.entity.Login;
import hotel.autentication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<Login> login(@RequestBody Login.LoginRequest loginRequest) {

    }


}

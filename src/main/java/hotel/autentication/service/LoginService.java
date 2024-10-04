package hotel.autentication.service;

import hotel.autentication.entity.Login;
import hotel.autentication.entity.User;
import hotel.autentication.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoginService {

    private JwtEncoder nimbusJwtEncoder;
    UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder JwtEncoder) {
        this.nimbusJwtEncoder = nimbusJwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Login.LoginResponse login(Login.LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.username()).orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!isCorrectPassword(loginRequest.password(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://example.com")
                .subject(user.getUuid().toString())
                .claim("username", user.getUserName())
                .expiresAt(Instant.now().plusSeconds(300L))
                .issuedAt(Instant.now())
                .claim("roles", user.getRoles())
                .build();

        String token = nimbusJwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new Login.LoginResponse(token, 300L);
    }


    private boolean isCorrectPassword(String password, String encodedPassword) {
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}

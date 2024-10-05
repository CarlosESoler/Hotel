package hotel.autentication.service;

import hotel.autentication.entity.LoginDTO;
import hotel.autentication.entity.Role;
import hotel.autentication.entity.User;
import hotel.autentication.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private final JwtEncoder JwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(UserRepository userRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        JwtEncoder JwtEncoder) {
        this.JwtEncoder = JwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginDTO.LoginResponse login(LoginDTO.LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.userName()).orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!isCorrectPassword(loginRequest.password(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://example.com")
                .subject(user.getUuid().toString())
                .claim("userName", user.getUserName())
                .expiresAt(Instant.now().plusSeconds(300L))
                .issuedAt(Instant.now())
                .claim("scope", user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(" ")))
                .build();

        String token = JwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginDTO.LoginResponse(token, 300L);
    }


    private boolean isCorrectPassword(String password, String encodedPassword) {
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}

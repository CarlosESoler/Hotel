package hotel.autentication.controller;

import hotel.autentication.entity.LoginDTO;
import hotel.autentication.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TokenController {

    private final LoginService loginService;

    public TokenController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO.LoginResponse> login(@RequestBody LoginDTO.LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
}

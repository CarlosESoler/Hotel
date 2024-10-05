package hotel.autentication.controller;

import hotel.autentication.entity.User;
import hotel.autentication.entity.UserDTO;
import hotel.autentication.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO.GetUser> register(@RequestBody UserDTO.CreateUser createUser) {
        return ResponseEntity.ok(userService.createUser(createUser));
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }


}

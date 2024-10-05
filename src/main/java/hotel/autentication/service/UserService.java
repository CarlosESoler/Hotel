package hotel.autentication.service;

import hotel.autentication.entity.Role;
import hotel.autentication.entity.User;
import hotel.autentication.entity.UserDTO;
import hotel.autentication.repository.RoleRepository;
import hotel.autentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Data
@Transactional(MANDATORY)
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO.GetUser createUser(UserDTO.CreateUser createUser) {
        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name().toLowerCase());

        userRepository.findByUserName(createUser.userName()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "User already exists");
        });

        User user = createUser.toEntity();
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return UserDTO.GetUser.fromUser(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}

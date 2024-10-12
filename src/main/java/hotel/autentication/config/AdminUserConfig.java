package hotel.autentication.config;

import hotel.autentication.entity.Role;
import hotel.autentication.entity.User;
import hotel.autentication.repository.RoleRepository;
import hotel.autentication.repository.UserRepository;
import hotel.domain.cache.CacheService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CacheService cacheService;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CacheService cacheService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cacheService = cacheService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        cacheService.clearCache("guests");
        Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        Optional<User> user = userRepository.findByUserName("admin");

        user.ifPresentOrElse(user1 -> {
                    System.out.println("User already exists");
                },
                () -> {
                    User newUser = new User();
                    newUser.setUserName("admin");
                    newUser.setPassword(bCryptPasswordEncoder.encode("admin"));
                    newUser.setRoles(Set.of(roleAdmin));
                    userRepository.save(newUser);
                });
    }
}

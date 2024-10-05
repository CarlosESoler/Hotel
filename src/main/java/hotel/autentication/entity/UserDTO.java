package hotel.autentication.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

public record UserDTO(String userName,
                      String password) {

    public record CreateUser(String userName,
                             String password) {

        private static BCryptPasswordEncoder passwordEncoder;

        public CreateUser {
            passwordEncoder = new BCryptPasswordEncoder();
        }

        public User toEntity() {
            return new User(userName, passwordEncoder.encode(password));
        }
    }

    public record GetUser(String userName,
                          Set<Role> roles) {

        public static GetUser fromUser(User user) {
            return new GetUser(user.getUserName(), user.getRoles());
        }
    }

}

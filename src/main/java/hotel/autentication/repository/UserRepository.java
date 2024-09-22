package hotel.autentication.repository;

import hotel.autentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UUID, User> {
    public Optional<User> findByUserName(String userName);
}

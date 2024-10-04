package hotel.autentication.repository;

import hotel.autentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Role findByName(String name);
}

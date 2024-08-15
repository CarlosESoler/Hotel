package hotel.domain.repository;

import hotel.data.entity.Guest;
import hotel.data.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Optional<Phone> findByGuest(Guest guest);
}

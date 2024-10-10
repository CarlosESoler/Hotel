package hotel.domain.repository;

import hotel.data.entity.Guest;
import hotel.data.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    final static String WHERE = "WHERE";

    Optional<Phone> findByGuest(Guest guest);

    @Query("SELECT p FROM Phone p WHERE p.guest.id = ?1")
    List<Phone> findByGuestId(int guestId);
}

package hotel.domain.repository;

import hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByRg(String rg);
}

package hotel.domain.repository;

import hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {

    Optional<Guest> findByRg(String rg);
    Optional<Guest> findByRgOrDocument(String rg, String document);
    Guest findByDocument(String document);

}

package hotel.domain.repository;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<List<Address>> findAddressByGuest(Guest guest);

    @Query("SELECT a FROM Address a WHERE a.guest.id = ?1")
    List<Address> findByGuestId(int id);
}

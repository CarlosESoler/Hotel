package hotel.domain.repository;

import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findAddressByGuest(Guest guest);
}

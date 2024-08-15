package hotel.domain.repository;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<List<Address>> findAddressByGuest(Guest guest);
}

package hotel.domain.repository;

import hotel.data.entity.guest.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

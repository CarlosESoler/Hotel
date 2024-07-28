package hotel.domain.repository;

import hotel.data.entity.Car;
import hotel.data.entity.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByLicensePlate(String licensePlate);
    Optional<Car> findByGuest(Guest guest);
}

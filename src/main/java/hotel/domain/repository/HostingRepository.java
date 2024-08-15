package hotel.domain.repository;

import hotel.data.entity.Hosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostingRepository extends JpaRepository<Hosting, Integer> {
    Hosting findByRoomNumber(String roomNumber);
}

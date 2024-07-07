package hotel.domain.repository;

import hotel.data.entity.hosting.Hosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostingRepository extends JpaRepository<Hosting, Integer> {
}

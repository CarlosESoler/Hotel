package hotel.domain.repository;

import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByNumber(String roomNumber);

    List<Room> findAllByStatus(RoomStatus status);
}

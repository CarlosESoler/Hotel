package br.com.hotel.domain.repository;

import br.com.hotel.data.model.room.Room;
import br.com.hotel.data.model.room.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Room findByNumber(String roomNumber);

    List<Room> findAllByStatus(RoomStatus status);
}

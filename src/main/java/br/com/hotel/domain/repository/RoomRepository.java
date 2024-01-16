package br.com.hotel.domain.repository;

import br.com.hotel.data.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Room findByRoomNumber(String roomNumber);
}

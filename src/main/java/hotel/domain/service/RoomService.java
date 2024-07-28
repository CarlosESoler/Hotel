package hotel.domain.service;

import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.exceptions.room.RoomAlreadyExistsException;
import hotel.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class RoomService {

    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Create a new room
     *
     * @param createRoomDTO
     * @return Room
     */
    public Room createRoom(CreateRoomDTO createRoomDTO) throws RoomAlreadyExistsException {
        Room foundedRoom = roomRepository.findByNumber(createRoomDTO.number());

        if(foundedRoom != null) {
            throw new RoomAlreadyExistsException();
        }

        return roomRepository.save(new Room(createRoomDTO));
    }

    /**
     * Search and get a room by room number
     *
     * @param roomNumber
     * @return room
     * @throws RuntimeException
     */
    public Room getRoomByNumber(String roomNumber) throws RoomNotFoundException {
        Room room = roomRepository.findByNumber(roomNumber);

        if (room == null) {
            throw new RoomNotFoundException();
        }

        return room;
    }

    public List<Room> getAllRoomsWithSpecificStatus(String status) {
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        return roomRepository.findAllByStatus(roomStatus);
    }

    public void updateRoomStatus(Room room, RoomStatus roomStatus) {
        room.setStatus(roomStatus);
        roomRepository.saveAndFlush(room);
    }
}

package br.com.hotel.domain.service;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.dto.room.GetRoomAndGuestDTO;
import br.com.hotel.data.model.guest.Guest;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.data.model.room.RoomStatus;
import br.com.hotel.domain.exceptions.room.RoomNotFoundException;
import br.com.hotel.domain.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    /**
     * Create a new room
     * @param dataRoom
     * @return Room
     */
    public Room createRoom(CreateRoomDTO dataRoom) {
        Optional.ofNullable(roomRepository.findByNumber(dataRoom.roomNumber()))
                .ifPresent(existingRoom -> {
                    throw new RuntimeException("O quarto já está registrado.");
                });

        Room room = new Room();
        BeanUtils.copyProperties(dataRoom, room);

        return roomRepository.save(room);
    }

    /**
     * Search and get a room by room number
     * @param roomNumber
     * @return room
     * @throws RuntimeException
     */
    public Room getRoomByNumber(String roomNumber) throws RoomNotFoundException {
        Room room = roomRepository.findByNumber(roomNumber);
        verifyIfRoomExistsByRoomNumber(room.getNumber());
        return room;
    }

    public RoomStatus verifyRoomStatus(String roomNumber) throws RoomNotFoundException {
        Room room = roomRepository.findByNumber(roomNumber);
        verifyIfRoomExistsByRoomNumber(room.getNumber());
        return room.getStatus();
    }

    /**
     * Get room and guest information's using a method called "Partial Resource", collecting only the necessary information's to show in the view
     * @param roomNumber
     * @return GetRoomAndGuestDTO
     */
    public GetRoomAndGuestDTO GetRoomAndGuest(@PathVariable String roomNumber) {
        Room room = roomRepository.findByNumber(roomNumber);

        Guest guest = room.getGuest();

        if(guest == null) {

        }
        return null;
    }

    public void roomGuestCheckIn(Guest guest, Room room) {
        room.setStatus(RoomStatus.OCCUPIED);
        room.setGuest(guest);
        roomRepository.save(room);
    }

    public List<Room> getAllRoomsWithSpecificStatus(String status) {
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        return roomRepository.findAllByStatus(roomStatus);
    }

    public void verifyIfRoomExistsByRoomNumber(String roomNumber) throws RoomNotFoundException {
        Room room = roomRepository.findByNumber(roomNumber);
        if(room == null) {
            throw new RoomNotFoundException("Quarto não encontrado");
        }
    }
}

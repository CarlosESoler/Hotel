package hotel.domain.service;

import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.domain.exceptions.room.RoomAlreadyExistsException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class RoomService {

    RoomRepository roomRepository;
    GuestService guestService;

    public RoomService(RoomRepository roomRepository, GuestService guestService) {
        this.roomRepository = roomRepository;
        this.guestService = guestService;
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

   /* public Object guestCheckIn(String guesRg, String roomNumber, CheckInRequestDTO guestDataCheckIn) throws RoomNotFoundException, GuestNotFoundException {
        Room room = getRoomByNumber(roomNumber);
        Guest guest = guestService.getGuestByRg(guesRg);
        if(room.getStatus().equals(RoomStatus.OCCUPIED)) {
            throw new RuntimeException("Quarto já está ocupado");
        }

        if(guest.getRoom() != null) {
            throw new RuntimeException("Hospede já está em um quarto");
        }

        room.setStatus(RoomStatus.OCCUPIED);
        room.setGuest(guestService.getGuestByRg(guesRg));

        guest.setRoom(room);
        roomRepository.save(room);

        return guestService.saveGuest(guest);


    } */
}

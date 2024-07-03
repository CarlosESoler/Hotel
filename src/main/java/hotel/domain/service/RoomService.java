package hotel.domain.service;

import hotel.data.dto.guest.CheckInRequestDTO;
import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
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
    public Room createRoom(CreateRoomDTO createRoomDTO) {
        Optional.ofNullable(roomRepository.findByNumber(createRoomDTO.number()))
                .ifPresent(existingRoom -> {
                    throw new RuntimeException("O quarto já está registrado.");
                });

        Room room = new Room(createRoomDTO);

        return roomRepository.save(room);
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
            throw new RoomNotFoundException("Quarto não encontrado");
        }
        return room;
    }

    public List<Room> getAllRoomsWithSpecificStatus(String status) {
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        return roomRepository.findAllByStatus(roomStatus);
    }

    public Object guestCheckIn(String guesRg, String roomNumber, CheckInRequestDTO guestDataCheckIn) throws RoomNotFoundException, GuestNotFoundException {
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
        guest.setCheckIn(guestDataCheckIn.checkIn());
        guest.setCheckOut(guestDataCheckIn.checkOut());
        roomRepository.save(room);

        return guestService.saveGuest(guest);


    }
}

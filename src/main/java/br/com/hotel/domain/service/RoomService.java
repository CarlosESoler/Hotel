package br.com.hotel.domain.service;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.exceptions.room.RoomNotFoundException;
import br.com.hotel.domain.repository.RoomRepository;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional.ofNullable(roomRepository.findByRoomNumber(dataRoom.roomNumber()))
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
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if(room == null) {
            throw new RoomNotFoundException("Quarto não encontrado");
        }
        return room;
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }
}

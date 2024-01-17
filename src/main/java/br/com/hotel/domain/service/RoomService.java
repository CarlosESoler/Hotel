package br.com.hotel.domain.service;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room createRoom(CreateRoomDTO dataRoom) {
        Optional.ofNullable(roomRepository.findByRoomNumber(dataRoom.roomNumber()))
                .ifPresent(existingRoom -> {
                    throw new RuntimeException("O quarto já está registrado.");
                });

        Room room = new Room();
        BeanUtils.copyProperties(dataRoom, room);

        return roomRepository.save(room);
    }

    public Room getRoomByNumber(String roomNumber) {
        if(roomRepository.findByRoomNumber(roomNumber) == null) {
            throw new RuntimeException("Quarto não encontrado");
        }
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }
}

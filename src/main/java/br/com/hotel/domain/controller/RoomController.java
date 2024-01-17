package br.com.hotel.domain.controller;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.repository.RoomRepository;
import br.com.hotel.domain.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomDTO dataRoom) {
        return ResponseEntity.ok(roomService.createRoom(dataRoom));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }
}

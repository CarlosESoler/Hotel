package br.com.hotel.domain.controller;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.exceptions.room.RoomNotFoundException;
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
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomDTO createDataRoom) {
        return ResponseEntity.ok(roomService.createRoom(createDataRoom));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> getRoomByNumber(@PathVariable String roomNumber) throws RoomNotFoundException {
        return ResponseEntity.ok(roomService.getRoomByNumber(roomNumber));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Room>> getAllRoomsWithSpecificStatus(@RequestParam String status) {
        return ResponseEntity.ok(roomService.getAllRoomsWithSpecificStatus(status));
    }
}

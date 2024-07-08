package hotel.domain.controller;

import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.room.Room;
import hotel.domain.exceptions.room.RoomAlreadyExistsException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.RoomRepository;
import hotel.domain.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/room")
public class RoomController {

    RoomService roomService;
    RoomRepository roomRepository;

    public RoomController(RoomService roomService, RoomRepository roomRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomDTO createDataRoom) throws RoomAlreadyExistsException {
        return ResponseEntity.ok(roomService.createRoom(createDataRoom));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> getRoomByNumber(@PathVariable String roomNumber) throws RoomNotFoundException {
        return ResponseEntity.ok(roomService.getRoomByNumber(roomNumber));
    }

    @GetMapping("/id/{roomId}")
    public ResponseEntity<Optional<Room>> getRoomById(@PathVariable int roomId) {
        return ResponseEntity.ok(roomRepository.findById(roomId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Room>> getAllRoomsWithSpecificStatus(@PathVariable String status) {
        return ResponseEntity.ok(roomService.getAllRoomsWithSpecificStatus(status));
    }

}

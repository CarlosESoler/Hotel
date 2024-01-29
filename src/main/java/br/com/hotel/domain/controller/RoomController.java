package br.com.hotel.domain.controller;

import br.com.hotel.data.dto.guest.CheckInRequestDTO;
import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.guest.Guest;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
import br.com.hotel.domain.exceptions.room.RoomNotFoundException;
import br.com.hotel.domain.repository.RoomRepository;
import br.com.hotel.domain.service.RoomService;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/hotel/rooms")
public class RoomController {

    RoomService roomService;
    RoomRepository roomRepository;

    public RoomController(RoomService roomService, RoomRepository roomRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

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

    @GetMapping("/id/{roomId}")
    public ResponseEntity<Optional<Room>> getRoomById(@PathVariable UUID roomId) {
        return ResponseEntity.ok(roomRepository.findById(roomId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Room>> getAllRoomsWithSpecificStatus(@PathVariable String status) {
        return ResponseEntity.ok(roomService.getAllRoomsWithSpecificStatus(status));
    }

    @PostMapping("/checkin/{guestRg}")
    public ResponseEntity<Object> guestCheckIn(@RequestHeader String roomNumber, @PathVariable String guestRg, @RequestBody CheckInRequestDTO guestDataCheckIn) throws RoomNotFoundException, GuestNotFoundException {
         return ResponseEntity.ok(roomService.guestCheckIn(guestRg, roomNumber, guestDataCheckIn));
    }
}

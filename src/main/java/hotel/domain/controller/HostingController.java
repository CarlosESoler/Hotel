package hotel.domain.controller;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.Hosting;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.service.HostingService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/hosting")
public class HostingController {
    HostingService hostingService;

    public HostingController(HostingService hostingService) {
        this.hostingService = hostingService;
    }

    @PostMapping
    public ResponseEntity<Hosting> createHosting(@RequestBody CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException {
        return ResponseEntity.ok(hostingService.createHosting(createHostingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hosting> getHostingById(@PathVariable int id) {
        return ResponseEntity.ok(hostingService.getHostingById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hosting>> getAllHostings() {
        return ResponseEntity.ok(hostingService.getAllHostings());
    }

    @GetMapping("/room/{roomNumber}")
    public ResponseEntity<Hosting> getHostingsByRoomNumber(@PathVariable String roomNumber) {
        return ResponseEntity.ok(hostingService.getHostingByRoomNumber(roomNumber));
    }
}

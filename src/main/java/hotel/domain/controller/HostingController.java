package hotel.domain.controller;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.hosting.Hosting;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.service.HostingService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/hosting")
public class HostingController {
    HostingService hostingService;

    public HostingController(HostingService hostingService) {
        this.hostingService = hostingService;
    }

    @PostMapping
    public ResponseEntity<Hosting> createHosting(@RequestBody CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException, GuestAddressNotFoundException {
        return ResponseEntity.ok(hostingService.createHosting(createHostingDTO));
    }
}

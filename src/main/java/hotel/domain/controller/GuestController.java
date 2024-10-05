package hotel.domain.controller;

import hotel.data.dto.GuestDTO;
import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.domain.service.GuestService;
import hotel.exceptions.guest.GuestAlreadyExistsWithRgException;
import hotel.exceptions.guest.GuestNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody GuestDTO.CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsWithRgException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guestService.createGuest(createGuestDTO));
    }

    @PatchMapping("/{rg}")
    public ResponseEntity<Guest> addGuestAddress(@PathVariable String rg, @RequestBody Address address) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.addAddressToGuest(rg, address));
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{rg}")
    public ResponseEntity<Guest> getGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.getGuestByRg(rg));
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllGuests() {
        return ResponseEntity.ok(guestService.deleteAllGuests());
    }

    @DeleteMapping("/{rg}")
    public ResponseEntity<String> deleteGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.deleteGuestByRg(rg));
    }
}

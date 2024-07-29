package hotel.domain.controller;


import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.guest.AbsoluteGuest;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestAlreadyExistsWithRgException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.service.GuestService;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsWithRgException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guestService.createGuest(createGuestDTO));
    }

    @GetMapping("/{rg}")
    public ResponseEntity<Guest> getGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.getGuestByRg(rg));
    }

    @DeleteMapping("/{rg}")
    public ResponseEntity<String> deleteGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.deleteGuestByRg(rg));
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllGuests() {
        return ResponseEntity.ok(guestService.deleteAllGuests());
    }

    @GetMapping("/absolute/{rg}")
    public ResponseEntity<AbsoluteGuest> getAbsoluteGuestByRg(@PathVariable String rg) throws GuestNotFoundException, GuestAddressNotFoundException {
        return ResponseEntity.ok(guestService.getAbsoluteGuestByRg(rg));
    }
}

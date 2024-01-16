package br.com.hotel.domain.controller;


import br.com.hotel.data.dto.guest.CheckInGuestDTO;
import br.com.hotel.data.dto.guest.CreateGuestDTO;
import br.com.hotel.data.model.Guest;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
import br.com.hotel.domain.repository.GuestRepository;
import br.com.hotel.domain.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody CreateGuestDTO createGuestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guestService.createGuest(createGuestDTO));
    }

    @GetMapping("/{rg}")
    public ResponseEntity<Guest> getGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.getGuestByRg(rg));
    }
    
    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @DeleteMapping("/{rg}")
    public ResponseEntity<String> deleteGuestByRg(@PathVariable String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.deleteGuestByRg(rg));
    }

    // TODO refactor this method to use a DTO, save a old guest and update it
    @PutMapping("/{rg}")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.updateGuest(guest.getRg(), guest));
    }

    @PutMapping("/checkin")
    public ResponseEntity<Guest> checkInGuest(@RequestBody CheckInGuestDTO guest) throws GuestNotFoundException {
        return ResponseEntity.ok(guestService.checkInGuest(guest));
    }
}

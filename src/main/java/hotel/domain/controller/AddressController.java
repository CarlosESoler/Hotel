package hotel.domain.controller;

import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.service.AddressService;
import hotel.domain.service.GuestService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;
    GuestService guestService;

    public AddressController(AddressService addressService, GuestService guestService) {
        this.addressService = addressService;
        this.guestService = guestService;
    }

    @GetMapping("/{guestRg}")
    public ResponseEntity<List<Address>> getAddressByGuest(@PathVariable String guestRg) throws GuestNotFoundException, GuestAddressNotFoundException {
        Guest guest = guestService.getGuestByRg(guestRg);
        return ResponseEntity.ok(addressService.getAddressesByGuest(guest));
    }
}
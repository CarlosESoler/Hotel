package hotel.domain.controller;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.domain.service.AddressService;
import hotel.domain.service.GuestService;
import hotel.exceptions.GuestAddressNotFoundException;
import hotel.exceptions.guest.GuestNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/addresses")
public class AddressController {

    AddressService addressService;
    GuestService guestService;

    public AddressController(AddressService addressService, GuestService guestService) {
        this.addressService = addressService;
        this.guestService = guestService;
    }

    @GetMapping("/{guestRg}")
    public ResponseEntity<List<Address>> getAddressesByGuest(@PathVariable String guestRg) throws GuestNotFoundException, GuestAddressNotFoundException {
        Guest guest = guestService.getGuestByRg(guestRg);
        return ResponseEntity.ok(addressService.getAddressesByGuest(guest));
    }
}
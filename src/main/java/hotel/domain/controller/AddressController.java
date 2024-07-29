package hotel.domain.controller;

import hotel.data.dto.address.CreateAddressDTO;
import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.service.AddressService;
import hotel.domain.service.GuestService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Address> getAddressByGuest(@PathVariable String guestRg) throws GuestNotFoundException, GuestAddressNotFoundException {
        Guest guest = guestService.getGuestByRg(guestRg);
        return ResponseEntity.ok(addressService.getAddressByGuest(guest));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDTO createAddressDTO) throws GuestNotFoundException {
        return ResponseEntity.ok(addressService.createAddress(createAddressDTO));
    }
}
package hotel.domain.controller;

import hotel.data.dto.phone.CreatePhoneDTO;
import hotel.data.dto.phone.GetPartialPhoneDTO;
import hotel.data.entity.Phone;
import hotel.data.entity.guest.Guest;
import hotel.exceptions.guest.GuestNotFoundException;
import hotel.domain.service.PhoneService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/phone")
public class PhoneController {
    PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody CreatePhoneDTO createPhoneDTO) throws GuestNotFoundException {
        return ResponseEntity.ok(phoneService.createPhone(createPhoneDTO));
    }

    @GetMapping
    public ResponseEntity<Phone> getPhone(@RequestParam String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(phoneService.getPhoneByGuest(rg));
    }

    @GetMapping("/partial")
    public ResponseEntity<GetPartialPhoneDTO> getPhoneById(@RequestParam String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(phoneService.getPartialPhoneByGuest(rg));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePhone() {
        phoneService.deleteAllPhones();
        return ResponseEntity.status(HttpStatus.OK).body("Tudo foi deletado");
    }
}

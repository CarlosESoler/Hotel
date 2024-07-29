package hotel.domain.controller;

import hotel.data.dto.guest.CreatePhoneDTO;
import hotel.data.dto.phone.GetPartialPhoneDTO;
import hotel.data.entity.Phone;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.guest.GuestNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Integer id) {
        return ResponseEntity.ok(phoneService.getPhoneById(id));
    }

    @GetMapping("/partial")
    public ResponseEntity<GetPartialPhoneDTO> getPhoneById(@RequestParam String rg) throws GuestNotFoundException {
        return ResponseEntity.ok(phoneService.getPartialPhoneByGuestRg(rg));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePhone() {
        phoneService.deleteAllPhones();
        return ResponseEntity.status(HttpStatus.OK).body("Tudo foi deletado");
    }
}

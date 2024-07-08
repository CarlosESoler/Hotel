package hotel.domain.controller;

import hotel.data.dto.guest.CreatePhoneDTO;
import hotel.data.entity.guest.Phone;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.service.PhoneService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

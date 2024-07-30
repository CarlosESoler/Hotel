package hotel.domain.controller;

import hotel.data.entity.Phone;
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

    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Integer id) {
        return ResponseEntity.ok(phoneService.getPhoneById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePhone() {
        phoneService.deleteAllPhones();
        return ResponseEntity.status(HttpStatus.OK).body("Tudo foi deletado");
    }
}

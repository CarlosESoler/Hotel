package hotel.domain.service;


import hotel.data.entity.Guest;
import hotel.data.entity.Phone;
import hotel.domain.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class PhoneService {
    PhoneRepository phoneRepository;
    GuestService guestService;

    public PhoneService(PhoneRepository phoneRepository, @Lazy GuestService guestService) {
        this.phoneRepository = phoneRepository;
        this.guestService = guestService;
    }

    public Phone getPhoneById(Integer id) {
        return phoneRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Phone getPhoneByGuest(Guest guest) {
        return phoneRepository.findByGuest(guest).orElseThrow(RuntimeException::new);
    }

    public void deleteAllPhones() {
        phoneRepository.deleteAll();
    }
}

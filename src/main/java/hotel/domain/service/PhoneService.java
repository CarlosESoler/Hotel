package hotel.domain.service;

import hotel.data.entity.guest.Phone;
import hotel.domain.repository.PhoneRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone getPhoneById(Integer id) {
        return phoneRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}

package hotel.domain.service;

import hotel.data.dto.guest.CreatePhoneDTO;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.guest.Phone;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.repository.GuestRepository;
import hotel.domain.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class PhoneService {
    PhoneRepository phoneRepository;
    GuestService guestService;

    public PhoneService(PhoneRepository phoneRepository, GuestService guestService) {
        this.phoneRepository = phoneRepository;
        this.guestService = guestService;
    }

    public Phone getPhoneById(Integer id) {
        return phoneRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Phone getPhoneByGuest(Guest guest) {
        return phoneRepository.findByGuest(guest).orElseThrow(RuntimeException::new);
    }

    public Phone createPhone(CreatePhoneDTO createPhoneDTO) throws GuestNotFoundException {
        Guest guest = guestService.getGuestByRg(createPhoneDTO.guestRg());
        Phone phone = new Phone(createPhoneDTO, guest);
        return phoneRepository.save(phone);
    }
}

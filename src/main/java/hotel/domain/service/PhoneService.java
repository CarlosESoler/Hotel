package hotel.domain.service;

import hotel.data.dto.phone.CreatePhoneDTO;
import hotel.data.dto.phone.GetPartialPhoneDTO;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.Phone;
import hotel.exceptions.guest.GuestNotFoundException;
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

    public Phone getPhoneByGuest(String rg) throws GuestNotFoundException {
        Guest guest = guestService.getGuestByRg(rg);
        return phoneRepository.findByGuest(guest).orElse(null);
    }

    public GetPartialPhoneDTO getPartialPhoneByGuest(String rg) throws GuestNotFoundException {
        Guest guest = guestService.getGuestByRg(rg);
        Phone phone = phoneRepository.findByGuest(guest).orElse(null);
        return new GetPartialPhoneDTO(phone.getId(), phone.getDdd(), phone.getCellPhone(), phone.getPhoneNumber());
    }

    public Phone createPhone(CreatePhoneDTO createPhoneDTO) throws GuestNotFoundException {
        Guest guest = guestService.getGuestByRg(createPhoneDTO.guestRg());
        Phone phone = new Phone(createPhoneDTO, guest);
        return phoneRepository.save(phone);
    }

    public void deleteAllPhones() {
        phoneRepository.deleteAll();
    }
}

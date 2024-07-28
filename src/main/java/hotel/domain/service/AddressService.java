package hotel.domain.service;

import hotel.data.dto.address.CreateAddressDTO;
import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import hotel.exceptions.guest.GuestAddressNotFoundException;
import hotel.exceptions.guest.GuestNotFoundException;
import hotel.domain.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class AddressService {

    AddressRepository addressRepository;
    GuestService guestService;

    public AddressService(AddressRepository addressRepository, @Lazy GuestService guestService) {
        this.addressRepository = addressRepository;
        this.guestService = guestService;
    }

    public Address getAddressByGuest(Guest guest) throws GuestAddressNotFoundException {
        return addressRepository.findAddressByGuest(guest).orElseThrow(GuestAddressNotFoundException::new);
    }

    public Address createAddress(CreateAddressDTO createAddressDTO) throws GuestNotFoundException {
        Guest guest = guestService.getGuestByRg(createAddressDTO.guestRg());
        return addressRepository.save(new Address(createAddressDTO, guestService.getGuestByRg(createAddressDTO.guestRg())));
    }
}

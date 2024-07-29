package hotel.domain.service;

import hotel.data.dto.address.CreateAddressDTO;
import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.repository.AddressRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
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

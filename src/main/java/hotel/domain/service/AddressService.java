package hotel.domain.service;

import hotel.data.entity.Address;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.repository.AddressRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    AddressRepository addressRepository;
    GuestService guestService;

    public AddressService(AddressRepository addressRepository, @Lazy GuestService guestService) {
        this.addressRepository = addressRepository;
        this.guestService = guestService;
    }

    public List<Address> getAddressesByGuest(Guest guest) throws GuestAddressNotFoundException {
        return addressRepository.findAddressByGuest(guest).orElseThrow(GuestAddressNotFoundException::new);
    }
}

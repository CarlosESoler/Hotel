package hotel.domain.service;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.domain.repository.AddressRepository;
import hotel.exceptions.GuestAddressNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Address> getAddressesByGuest(Guest guest) {
        return addressRepository.findAddressByGuest(guest).orElseThrow(GuestAddressNotFoundException::new);
    }
}

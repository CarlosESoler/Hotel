package hotel.domain.service;

import hotel.data.entity.guest.Address;
import hotel.data.entity.guest.Guest;
import hotel.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressByGuest(Guest guest) {
        return addressRepository.findAddressByGuest(guest).orElseThrow(RuntimeException::new);
    }
}

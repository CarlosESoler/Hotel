package hotel.domain.service;

import hotel.data.entity.guest.Address;
import hotel.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}

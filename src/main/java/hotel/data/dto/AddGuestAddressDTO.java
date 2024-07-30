package hotel.data.dto;

import hotel.data.entity.Address;

public record AddGuestAddressDTO(
        String rg,
        Address address
) {
}

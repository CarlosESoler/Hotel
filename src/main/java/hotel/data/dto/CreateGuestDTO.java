package hotel.data.dto;

import hotel.data.entity.Address;
import hotel.data.entity.Phone;

import java.time.LocalDate;

public record CreateGuestDTO(
        String rg,
        String document,
        LocalDate dateOfBirth,
        String name,
        String lastName,
        String motherName,
        String email,
        Phone phone,
        Address address
) {
}

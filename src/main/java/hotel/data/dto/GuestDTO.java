package hotel.data.dto;

import hotel.data.entity.Address;
import hotel.data.entity.Phone;

import java.time.LocalDate;
import java.util.List;

public record GuestDTO(
        String rg,
        String document,
        LocalDate dateOfBirth,
        String name,
        String lastName,
        String motherName,
        String email,
        List<Phone> phones,
        List<Address> addresses) {
}

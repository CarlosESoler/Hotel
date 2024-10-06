package hotel.data.dto;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.data.entity.Phone;

import java.time.LocalDate;
import java.util.List;

public record GuestDTO() {
    public record CreateGuestDTO(
            String rg,
            String document,
            LocalDate dateOfBirth,
            String name,
            String lastName,
            String motherName,
            String email,
            List<Phone> phones,
            List<Address> addresses
    ) {
        public static Guest toEntity(CreateGuestDTO createGuestDTO) {
            Guest guest = new Guest();
            guest.setRg(createGuestDTO.rg());
            guest.setDocument(createGuestDTO.document());
            guest.setDateOfBirth(createGuestDTO.dateOfBirth());
            guest.setName(createGuestDTO.name());
            guest.setLastName(createGuestDTO.lastName());
            guest.setMotherName(createGuestDTO.motherName());
            guest.setEmail(createGuestDTO.email());
            guest.setPhones(createGuestDTO.phones());
            guest.setAddresses(createGuestDTO.addresses());
            return guest;
        }
    }
}

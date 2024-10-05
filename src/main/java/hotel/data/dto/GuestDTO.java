package hotel.data.dto;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
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

    public Guest toEntity() {
        Guest guest = new Guest();
        guest.setRg(rg());
        guest.setDocument(document());
        guest.setDateOfBirth(dateOfBirth());
        guest.setName(name());
        guest.setLastName(lastName());
        guest.setMotherName(motherName());
        guest.setEmail(email());
        guest.setPhones(phones());
        guest.setAddresses(addresses());
        return guest;
    }

    public static GuestDTO fromEntity(Guest guest) {
        return new GuestDTO(
                guest.getRg(),
                guest.getDocument(),
                guest.getDateOfBirth(),
                guest.getName(),
                guest.getLastName(),
                guest.getMotherName(),
                guest.getEmail(),
                guest.getPhones(),
                guest.getAddresses()
        );
    }

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

        public GuestDTO toGuestDTO() {
            return new GuestDTO(
                    rg(),
                    document(),
                    dateOfBirth(),
                    name(),
                    lastName(),
                    motherName(),
                    email(),
                    List.of(phone),
                    List.of(address)
            );
        }
    }

    public record GetGuestDTO(
            String rg,
            String name,
            String lastName,
            String motherName,
            String email,
            List<Phone> phones
    ) {
        public static GetGuestDTO fromEntity(Guest guest) {
            return new GetGuestDTO(
                    guest.getRg(),
                    guest.getName(),
                    guest.getLastName(),
                    guest.getMotherName(),
                    guest.getEmail(),
                    guest.getPhones()
            );
        }
    }
}

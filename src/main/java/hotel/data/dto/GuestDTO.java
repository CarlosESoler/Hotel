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
        Guest guest = Guest.builder()
                .rg(rg())
                .document(document())
                .dateOfBirth(dateOfBirth())
                .name(name())
                .lastName(lastName())
                .motherName(motherName())
                .email(email())
                .phones(phones())
                .addresses(addresses())
                .build();
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
}

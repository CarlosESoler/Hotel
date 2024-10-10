package hotel.data.dto;

import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.data.entity.Phone;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record GuestDTO(
        String rg,
        String document,
        LocalDate dateOfBirth,
        String name,
        String lastName,
        String motherName,
        String email
) {
    public static GuestDTO toDTO(Guest guest) {
        return new GuestDTO(
                guest.getRg(),
                guest.getDocument(),
                guest.getDateOfBirth(),
                guest.getName(),
                guest.getLastName(),
                guest.getMotherName(),
                guest.getEmail()
        );
    }

    public record CreateGuestDTO(
            String rg,
            String document,
            LocalDate dateOfBirth,
            String name,
            String lastName,
            String motherName,
            String email
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
            return guest;
        }
    }

    public record GetGuestDTO(
            String rg,
            String document,
            LocalDate dateOfBirth,
            String name,
            String lastName,
            String motherName,
            String email,
            List<Address> addresses,
            List<Phone> phones
    ) implements Serializable {

        public static GetGuestDTO toDTO(Guest guest, List<Address> addresses, List<Phone> phones) {
            return new GetGuestDTO(
                    guest.getRg(),
                    guest.getDocument(),
                    guest.getDateOfBirth(),
                    guest.getName(),
                    guest.getLastName(),
                    guest.getMotherName(),
                    guest.getEmail(),
                    addresses,
                    phones
            );
        }
    }
}

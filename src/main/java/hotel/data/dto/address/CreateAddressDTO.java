package hotel.data.dto.address;

public record CreateAddressDTO(
        String zipCode,
        String reference,
        int houseNumber,
        String state,
        String city,
        String guestRg
) {
}

package hotel.data.dto.phone;

public record GetPartialPhoneDTO(
        int id,
        String ddd,
        String cellPhone,
        String phoneNumber
) {
}
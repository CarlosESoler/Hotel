package hotel.data.dto.guest;

import java.time.LocalDate;

public record CreateGuestDTO(
        String rg,
        String document,
        LocalDate dateOfBirth,
        String name,
        String lastName,
        String motherName,
        String email
) {
}

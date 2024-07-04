package hotel.data.dto.guest;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

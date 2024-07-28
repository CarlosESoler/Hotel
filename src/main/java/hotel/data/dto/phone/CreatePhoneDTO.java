package hotel.data.dto.phone;

import jakarta.annotation.Nullable;

public record CreatePhoneDTO(
        String ddd,
        String cellPhone,
        @Nullable
        String phoneNumber,
        String guestRg
) {
}

package hotel.data.dto.guest;

import jakarta.annotation.Nullable;

public record CreatePhoneDTO(
        String ddd,
        String cellPhone,
        @Nullable
        String phoneNumber,
        String guestRg
) {
}

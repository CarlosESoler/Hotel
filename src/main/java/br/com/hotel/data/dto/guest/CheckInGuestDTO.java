package br.com.hotel.data.dto.guest;

import java.time.LocalDateTime;

public record CheckInGuestDTO(
        String rg,
        String roomNumber,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
}

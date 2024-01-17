package br.com.hotel.data.dto.guest;

import java.time.LocalDateTime;

public record CheckInGuestDTO(
        String document,
        String roomNumber,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
}

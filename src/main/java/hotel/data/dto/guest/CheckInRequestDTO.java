package hotel.data.dto.guest;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CheckInRequestDTO(

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkIn,

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkOut
) {
}

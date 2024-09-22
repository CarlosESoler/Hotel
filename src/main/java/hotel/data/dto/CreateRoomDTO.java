package hotel.data.dto;

import hotel.data.entity.room.RoomStatus;
import hotel.data.entity.room.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CreateRoomDTO(
        String number,
        String observation,
        @Enumerated(EnumType.STRING)
        RoomStatus status,
        @Enumerated(EnumType.STRING)
        RoomType type
) {
}

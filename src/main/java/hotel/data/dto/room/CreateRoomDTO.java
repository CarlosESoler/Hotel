package hotel.data.dto.room;

import hotel.data.model.room.RoomStatus;
import hotel.data.model.room.RoomType;
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

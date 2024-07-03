package hotel.data.dto.room;

import hotel.data.entity.guest.Guest;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.data.entity.room.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record GetRoomAndGuestDTO(
        String roomNumber,
        String observation,
        @Enumerated(EnumType.STRING)
        RoomStatus status,
        @Enumerated(EnumType.STRING)
        RoomType type,

        // Guest information's
        String guestName,
        String guestDocument
) {
    public GetRoomAndGuestDTO(Room room, Guest Guest) {
        this(
                room.getNumber(),
                room.getObservation(),
                room.getStatus(),
                room.getType(),
                Guest.getFullName(),
                Guest.getDocument()
        );
    }
}

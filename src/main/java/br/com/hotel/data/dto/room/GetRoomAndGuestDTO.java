package br.com.hotel.data.dto.room;

import br.com.hotel.data.model.guest.Guest;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.data.model.room.RoomStatus;
import br.com.hotel.data.model.room.RoomType;
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
        String guestDocument,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
    public GetRoomAndGuestDTO(Room room, Guest Guest) {
        this(
                room.getNumber(),
                room.getObservation(),
                room.getStatus(),
                room.getType(),
                Guest.getFullName(),
                Guest.getDocument(),
                Guest.getCheckIn(),
                Guest.getCheckOut()
        );
    }
}

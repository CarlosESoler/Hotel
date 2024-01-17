package br.com.hotel.data.dto.room;

import br.com.hotel.data.model.room.RoomType;
import br.com.hotel.data.model.room.RoomStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CreateRoomDTO(
        String roomNumber,
        String observation,
        @Enumerated(EnumType.STRING)
        RoomStatus roomStatus,
        @Enumerated(EnumType.STRING)
         RoomType type
){
}

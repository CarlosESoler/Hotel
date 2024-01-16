package br.com.hotel.data.dto.room;

import br.com.hotel.data.enums.RoomType;
import br.com.hotel.data.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CreateRoomDTO(
        String roomNumber,
        String observation,
        @Enumerated(EnumType.STRING)
        Status status,
        @Enumerated(EnumType.STRING)
         RoomType type
){
}

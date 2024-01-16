package br.com.hotel.data.dto.room;

import br.com.hotel.data.model.Guest;

import java.util.UUID;

public record CreateRoomDTO(
        String roomNumber
) {
}

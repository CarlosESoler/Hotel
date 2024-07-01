package br.com.hotel.data.model.room;

import br.com.hotel.data.dto.room.CreateRoomDTO;
import br.com.hotel.data.model.guest.Guest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
public class Room {

    @Id
    @UuidGenerator
    private UUID id;

    private String number;

    private String observation;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Guest guest;

    public Room(CreateRoomDTO createRoomDTO) {
        this.number = createRoomDTO.number();
        this.observation = createRoomDTO.observation();
        this.status = createRoomDTO.status();
        this.type = createRoomDTO.type();
    }

    public Room() {
    }
}

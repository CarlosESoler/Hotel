package hotel.data.entity.room;

import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.guest.Guest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private String observation;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    public Room(CreateRoomDTO createRoomDTO) {
        this.number = createRoomDTO.number();
        this.observation = createRoomDTO.observation();
        this.status = createRoomDTO.status();
        this.type = createRoomDTO.type();
    }

    public Room() {
    }
}



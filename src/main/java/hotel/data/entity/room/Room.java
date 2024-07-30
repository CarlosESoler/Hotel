package hotel.data.entity.room;

import hotel.data.dto.room.CreateRoomDTO;
import hotel.data.entity.guest.Guest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import hotel.data.entity.hosting.Hosting;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Hosting> hosting;

    public Room(CreateRoomDTO createRoomDTO) {
        this.number = createRoomDTO.number();
        this.observation = createRoomDTO.observation();
        this.status = createRoomDTO.status();
        this.type = createRoomDTO.type();
    }

    public Room() {
    }
}



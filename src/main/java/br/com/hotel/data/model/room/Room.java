package br.com.hotel.data.model.room;

import br.com.hotel.data.model.guest.Guest;
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

    @OneToOne
    private Guest guest;
}

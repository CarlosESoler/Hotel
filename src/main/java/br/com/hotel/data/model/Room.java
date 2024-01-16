package br.com.hotel.data.model;

import br.com.hotel.data.enums.RoomType;
import br.com.hotel.data.enums.Status;
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

    private String roomNumber;

    private String observation;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @OneToOne
    private Guest guest;
}

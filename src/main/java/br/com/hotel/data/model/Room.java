package br.com.hotel.data.model;

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

    @OneToOne
    private Guest guest;
}

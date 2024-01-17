package br.com.hotel.data.model.guest;

import br.com.hotel.data.model.room.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Guest {

    @Id
    @UuidGenerator
    private UUID id;

    private String rg;
    private String document;
    private String carNumber;
    private LocalDate birthDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String fullName;
    private String phone;
    private String motherName;
    private String email;

    // TODO - Make a list of companions

    private String address;
    private String zipCode;
    private String city;
    private String state;

    @OneToOne
    private Room room;
}

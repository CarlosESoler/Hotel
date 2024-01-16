package br.com.hotel.data.model;

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
    private String cpf;
    private String carNumber;
    private LocalDate birthDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String fullName;
    private String phone;
    private String motherName;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String email;

    // TODO - Make a list of companions
    // TODO - Make a list of rooms

    @OneToOne
    private Room room;
}

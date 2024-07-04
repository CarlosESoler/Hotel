package hotel.data.entity;

import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rg;
    private String document;
    private LocalDate dateOfBirth;
    private String name;
    private String lastName;
    private String motherName;
    private String email;

    @CurrentTimestamp
    private LocalDateTime createdAt;

    // TODO - Make a list of companions

    public Guest(CreateGuestDTO createGuestDTO) {
        this.rg = createGuestDTO.rg();
        this.document = createGuestDTO.document();
        this.dateOfBirth = createGuestDTO.dateOfBirth();
        this.name = createGuestDTO.name();
        this.lastName = createGuestDTO.lastName();
        this.motherName = createGuestDTO.motherName();
        this.email = createGuestDTO.email();
    }

    public Guest() {

    }
}

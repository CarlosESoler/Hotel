package hotel.data.entity.guest;

import hotel.data.dto.guest.CheckInRequestDTO;
import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

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
    private LocalDate birthDate;

    private String fullName;
    private String phone;
    private String motherName;
    private String email;
    // TODO - Make a list of companions

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Room room;

    public Guest(CreateGuestDTO createGuestDTO) {
        this.rg = createGuestDTO.rg();
        this.document = createGuestDTO.document();
        this.birthDate = createGuestDTO.birthDate();
        this.fullName = createGuestDTO.fullName();
        this.phone = createGuestDTO.phone();
        this.motherName = createGuestDTO.motherName();
        this.email = createGuestDTO.email();
    }

    public Guest() {
    }

}

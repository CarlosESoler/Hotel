package hotel.data.model.guest;

import hotel.data.dto.guest.CheckInRequestDTO;
import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.model.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Room room;

    public Guest(CreateGuestDTO createGuestDTO) {
        this.rg = createGuestDTO.rg();
        this.document = createGuestDTO.document();
        this.carNumber = createGuestDTO.carNumber();
        this.birthDate = createGuestDTO.birthDate();
        this.fullName = createGuestDTO.fullName();
        this.phone = createGuestDTO.phone();
        this.motherName = createGuestDTO.motherName();
        this.email = createGuestDTO.email();
        this.address = createGuestDTO.address();
        this.zipCode = createGuestDTO.zipCode();
        this.city = createGuestDTO.city();
        this.state = createGuestDTO.state();
    }

    public Guest() {
    }

    public Guest(CheckInRequestDTO checkInRequestDTO) {
        this.setCheckIn(checkInRequestDTO.checkIn());
        this.setCheckOut(checkInRequestDTO.checkOut());
    }
}

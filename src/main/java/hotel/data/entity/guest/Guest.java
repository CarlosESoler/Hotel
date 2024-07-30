package hotel.data.entity.guest;

import hotel.data.dto.CreateGuestDTO;
import hotel.data.entity.Address;
import hotel.data.entity.Phone;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    // TODO - Make a list of companions

    public Guest(CreateGuestDTO createGuestDTO) {
        this.rg = createGuestDTO.rg();
        this.document = createGuestDTO.document();
        this.dateOfBirth = createGuestDTO.dateOfBirth();
        this.name = createGuestDTO.name();
        this.lastName = createGuestDTO.lastName();
        this.motherName = createGuestDTO.motherName();
        this.email = createGuestDTO.email();

        this.addresses.add(new Address(createGuestDTO.address(), this));
        this.phones.add(new Phone(createGuestDTO.phone(), this));
    }

    public Guest() {

    }
}

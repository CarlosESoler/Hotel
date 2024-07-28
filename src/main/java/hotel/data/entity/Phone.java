package hotel.data.entity;

import hotel.data.dto.phone.CreatePhoneDTO;
import hotel.data.entity.guest.Guest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5)
    private String ddd;

    @Column(length = 15)
    private String cellPhone;

    @Column(length = 15)
    @Null
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;

    public Phone(CreatePhoneDTO createPhoneDTO, Guest guest) {
        this.ddd = createPhoneDTO.ddd();
        this.cellPhone = createPhoneDTO.cellPhone();
        this.phoneNumber = createPhoneDTO.phoneNumber();
        this.guest = guest;
    }

    public Phone() {
    }
}

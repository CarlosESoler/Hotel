package hotel.data.entity.guest;

import hotel.data.dto.guest.CreatePhoneDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;

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

    @ManyToOne
    private Guest guest;

    public Phone(CreatePhoneDTO createPhoneDTO, Guest guest) {
        this.ddd = createPhoneDTO.ddd();
        this.cellPhone = createPhoneDTO.cellPhone();
        this.phoneNumber = createPhoneDTO.phoneNumber();
        this.guest = guest;
    }
}

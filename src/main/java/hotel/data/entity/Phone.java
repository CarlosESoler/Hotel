package hotel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Guest guest;

    public Phone(Phone phone, Guest guest) {
        this.ddd = phone.getDdd();
        this.cellPhone = phone.getCellPhone();
        this.phoneNumber = phone.getPhoneNumber();
        this.guest = guest;
    }

    public Phone() {
    }
}

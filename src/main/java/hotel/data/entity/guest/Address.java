package hotel.data.entity.guest;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String zipCode;
    private String city;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;
}

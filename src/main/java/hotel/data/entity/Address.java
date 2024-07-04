package hotel.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 9)
    private String zipCode;
    private String reference;
    private int houseNumber;
    @Column(length = 25)
    private String state;
    @Column(length = 25)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;
}

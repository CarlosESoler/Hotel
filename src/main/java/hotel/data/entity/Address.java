package hotel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hotel.data.entity.guest.Guest;
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
    @JsonIgnore
    private Guest guest;

    public Address(Address address, Guest guest) {
        this.city = address.getCity();
        this.state = address.getState();
        this.houseNumber = address.getHouseNumber();
        this.reference = address.getReference();
        this.zipCode = address.getZipCode();
        this.guest = guest;
    }

    public Address() {
    }
}
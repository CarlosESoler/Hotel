package hotel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Address implements Serializable {
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
}
package hotel.data.entity.guest;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 15)
    private String licensePlate;
    @Column(length = 4)
    private String year;
    @Column(length = 30)
    private String model;
    @Column(length = 10)
    private String color;
}

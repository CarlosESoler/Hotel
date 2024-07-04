package hotel.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5)
    private String ddd;

    @Column(length = 9)
    private String cellPhone;

    @Column(length = 9)
    private String phoneNumber;

    @ManyToOne
    Guest guest;
}

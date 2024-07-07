package hotel.data.entity.hosting;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.guest.Address;
import hotel.data.entity.guest.Car;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.guest.Phone;
import hotel.data.entity.room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Hosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreatedDate
    private LocalDateTime checkIn;
    @LastModifiedDate
    private LocalDateTime checkOut;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Address address;

    @ManyToOne
    @Null
    private Car car;

    @ManyToOne
    private Phone phone;

    public Hosting() {
    }

    public Hosting(Guest guest, Room room, Phone phone, Address address) {
        this.guest = guest;
        this.room = room;
        this.phone = phone;
        this.address = address;
    }
}

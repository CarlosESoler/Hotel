package hotel.data.entity.hosting;

import hotel.data.entity.Address;
import hotel.data.entity.Car;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.Phone;
import hotel.data.entity.room.Room;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Hosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CurrentTimestamp
    private LocalDateTime checkIn;
    @LastModifiedDate
    private LocalDateTime checkOut;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @Null
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    private Phone phone;

    public Hosting() {
    }

    public Hosting(Room room, Address address, @Nullable Car car, Phone phone, Guest guest) {
        this.room = room;
        this.address = address;
        this.car = car;
        this.phone = phone;
        this.guest = guest;
    }
}

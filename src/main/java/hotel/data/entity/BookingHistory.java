package hotel.data.entity;

import hotel.data.entity.guest.Car;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.room.Room;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date checkIn;
    private Date checkOut;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Car car;
}

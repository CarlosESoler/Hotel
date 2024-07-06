package hotel.data.entity;

import hotel.data.entity.guest.Car;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class BookingHistory {

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
    @Null
    private Car car;
}

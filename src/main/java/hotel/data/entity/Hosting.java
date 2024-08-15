package hotel.data.entity;

import hotel.data.entity.room.Room;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
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
    @Null
    private Car car;

    public Hosting() {
    }

    public Hosting(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
    }
}
package hotel.data.dto;

import hotel.data.entity.Car;
import hotel.data.entity.Guest;
import hotel.data.entity.Hosting;
import hotel.data.entity.room.Room;
import jakarta.annotation.Nullable;

public record HostingDTO(
        Room room,
        @Nullable Car car,
        Guest guest
) {
    public HostingDTO fromHosting(Hosting hosting) {
        return new HostingDTO(
                hosting.getRoom(),
                hosting.getCar(),
                hosting.getGuest()
        );
    }

    public Hosting toEntity() {
        return new Hosting(
                room,
                guest
        );
    }

    public record CreateHostingDTO(
            String roomNumber,
            String guestRg,
            @Nullable Car car
    ) {
        public HostingDTO toHostingDTO(Room room, @Nullable Car car, Guest guest) {
            return new HostingDTO(
                    room,
                    car,
                    guest
            );

        }
    }
}

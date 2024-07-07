package hotel.data.dto.hosting;

import hotel.data.entity.guest.Address;
import hotel.data.entity.guest.Car;
import hotel.data.entity.guest.Phone;
import jakarta.validation.constraints.Null;

public record CreateHostingDTO(
        String roomNumber,
        String guestRg,
        @Null
        Car guestCar,
        @Null
        Address guestAddress,
        @Null
        Phone guestPhone
) {
}

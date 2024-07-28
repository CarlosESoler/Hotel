package hotel.data.entity.guest;

import hotel.data.dto.phone.GetPartialPhoneDTO;
import hotel.data.entity.Address;
import hotel.data.entity.Car;
import lombok.Data;

@Data
public class AbsoluteGuest {
    private Guest guest;
    private GetPartialPhoneDTO phone;
    private Address address;
    private Car car;

    public AbsoluteGuest(Guest guest, GetPartialPhoneDTO phone, Address address, Car car) {
        this.guest = guest;
        this.phone = phone;
        this.address = address;
        this.car = car;
    }
}

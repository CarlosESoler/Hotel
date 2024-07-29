package hotel.domain.service;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.Address;
import hotel.data.entity.Car;
import hotel.data.entity.Phone;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.hosting.Hosting;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.domain.exceptions.GuestAddressNotFoundException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.HostingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HostingService {

    private final HostingRepository hostingRepository;
    private final RoomService roomService;
    private final GuestService guestService;
    private final CarService carService;
    private final AddressService addressService;
    private final PhoneService phoneService;

    public HostingService(HostingRepository hostingRepository, RoomService roomService, GuestService guestService, CarService carService, AddressService addressService, PhoneService phoneService) {
        this.hostingRepository = hostingRepository;
        this.roomService = roomService;
        this.guestService = guestService;
        this.carService = carService;
        this.addressService = addressService;
        this.phoneService = phoneService;
    }

    @Transactional(rollbackOn = Exception.class)
    public Hosting createHosting(CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException, GuestAddressNotFoundException {
        Room room = roomService.getRoomByNumber(createHostingDTO.roomNumber());
        Guest guest = guestService.getGuestByRg(createHostingDTO.guestRg());
        Address address = addressService.getAddressByGuest(guest);
        Phone phone = phoneService.getPhoneByGuest(guest);
        Car car = carService.getCarByGuest(guest);

        roomService.updateRoomStatus(room, RoomStatus.OCCUPIED);
        Hosting hosting = new Hosting(room, address, car, phone, guest);

        return hostingRepository.save(hosting);
    }

}

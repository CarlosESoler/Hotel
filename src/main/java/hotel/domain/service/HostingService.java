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

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
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

    public Hosting createHosting(CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException {
        Room room = roomService.getRoomByNumber(createHostingDTO.roomNumber());

        if(room.getStatus() != RoomStatus.AVAILABLE) {
            throw new IllegalArgumentException("Room is not available");
        }

        Guest guest = guestService.getGuestByRg(createHostingDTO.guestRg());
        Car car = carService.getCarByGuest(guest);

        roomService.updateRoomStatus(room, RoomStatus.OCCUPIED);
        Hosting hosting = new Hosting(room, car, guest);

        return hostingRepository.save(hosting);
    }

}

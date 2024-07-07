package hotel.domain.service;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.guest.Address;
import hotel.data.entity.guest.Car;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.guest.Phone;
import hotel.data.entity.hosting.Hosting;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.HostingRepository;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HostingService {

    HostingRepository hostingRepository;
    RoomService roomService;
    GuestService guestService;
    CarService carService;
    AddressService addressService;
    PhoneService phoneService;


    public HostingService(HostingRepository hostingRepository) {
        this.hostingRepository = hostingRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public Hosting createHosting(CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException {
        if(createHostingDTO.guestPhoneId() == null)
            throw new IllegalArgumentException("Phone id is required");

        if(createHostingDTO.guestAddressId() == null)
            throw new IllegalArgumentException("Address id is required");

        Room room = roomService.getRoomByNumber(createHostingDTO.roomNumber());
        Guest guest = guestService.getGuestByRg(createHostingDTO.guestRg());
        Phone phone = phoneService.getPhoneById(createHostingDTO.guestPhoneId());
        Address address = addressService.getAddressById(createHostingDTO.guestAddressId());

        if(createHostingDTO.guestCarId() != null) {
            Car car = carService.getCarById(createHostingDTO.guestCarId());

        }

        roomService.updateRoomStatus(room, RoomStatus.OCCUPIED);
        Hosting hosting = new Hosting(guest, room, phone, address);

        return hostingRepository.save(hosting);
    }

}

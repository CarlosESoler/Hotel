package hotel.domain.service;

import hotel.data.dto.HostingDTO;
import hotel.data.entity.Car;
import hotel.data.entity.Guest;
import hotel.data.entity.Hosting;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.domain.repository.HostingRepository;
import hotel.exceptions.guest.GuestNotFoundException;
import hotel.exceptions.room.RoomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class HostingService {

    private final HostingRepository hostingRepository;
    private final RoomService roomService;
    private final GuestService guestService;
    private final CarService carService;

    public HostingService(HostingRepository hostingRepository, RoomService roomService, GuestService guestService, CarService carService) {
        this.hostingRepository = hostingRepository;
        this.roomService = roomService;
        this.guestService = guestService;
        this.carService = carService;
    }

    public Hosting createHosting(HostingDTO.CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException {
        Room room = roomService.getRoomByNumber(createHostingDTO.roomNumber());

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new IllegalArgumentException("Room is not available");
        }

        Guest guest = guestService.getGuestByRg(createHostingDTO.guestRg());
        Car car = carService.getCarByGuest(guest);

        roomService.updateRoomStatus(room, RoomStatus.OCCUPIED);
        Hosting hosting = createHostingDTO.toHostingDTO(room, car, guest).toEntity();

        return hostingRepository.save(hosting);
    }

    public List<Hosting> getAllHostings() {
        return hostingRepository.findAll().stream().filter(hosting -> hosting.getRoom().getStatus() == RoomStatus.OCCUPIED).toList();
    }

    public Hosting getHostingById(int id) {
        return hostingRepository.findById(id).orElse(null);
    }

    public Hosting getHostingByRoomNumber(String roomNumber) {
        return hostingRepository.findByRoomNumber(roomNumber);
    }
}

package hotel.domain.service;

import hotel.data.dto.hosting.CreateHostingDTO;
import hotel.data.entity.guest.Guest;
import hotel.data.entity.hosting.Hosting;
import hotel.data.entity.room.Room;
import hotel.data.entity.room.RoomStatus;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.exceptions.room.RoomNotFoundException;
import hotel.domain.repository.HostingRepository;
import org.springframework.stereotype.Service;

@Service
public class HostingService {

    HostingRepository hostingRepository;
    RoomService roomService;
    GuestService guestService;

    public HostingService(HostingRepository hostingRepository) {
        this.hostingRepository = hostingRepository;
    }

    public Hosting createHosting(CreateHostingDTO createHostingDTO) throws RoomNotFoundException, GuestNotFoundException {
        Room room = roomService.getRoomByNumber(createHostingDTO.roomNumber());
        Guest guest = guestService.getGuestByRg(createHostingDTO.guestRg());

        roomService.updateRoomStatus(room, RoomStatus.OCCUPIED);

        Hosting hosting = new Hosting();

        return hostingRepository.save(hosting);
    }

}

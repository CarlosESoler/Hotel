package br.com.hotel.domain.service;

import br.com.hotel.data.dto.guest.CheckInGuestDTO;
import br.com.hotel.data.dto.guest.CreateGuestDTO;
import br.com.hotel.data.model.guest.Guest;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.exceptions.guest.GuestAlreadyExistsException;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
import br.com.hotel.domain.exceptions.room.RoomNotFoundException;
import br.com.hotel.domain.repository.GuestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomService roomService;

    /**
     * Create a new guest
     * @param createGuestDTO
     * @return Guest
     * @throws GuestAlreadyExistsException
     */
    public Guest createGuest(CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsException {
        Optional.ofNullable(guestRepository.findByRgOrDocument(createGuestDTO.rg(), createGuestDTO.document()))
        .orElseThrow(() -> new GuestAlreadyExistsException("Hospede já cadastrado com esse RG ou CPF/CPNJ: ", createGuestDTO.document()));

        Guest guest = new Guest();
        BeanUtils.copyProperties(createGuestDTO, guest);
        return guestRepository.save(guest);
    }

    /**
     * Search and get a guest by rg
     * @param rg
     * @return Guest
     */
    public Guest getGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = guestRepository.findByRg(rg);
        verifyIfGuestExists(guest.getRg());
        return guest;
    }

    /**
     * Delete a guest by rg
     * @param rg
     * @return String
     * @throws GuestNotFoundException
     */
    public String deleteGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = getGuestByRg(rg);
        guestRepository.delete(guest);
        return "Hospede deletado com sucesso";
    }

    /**
     * Get all guests
     * @return List<Guest>
     */
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    /**
     * Check in a guest and set a room
     * @return Guest with room
     * @throws GuestNotFoundException
     */
    @Transactional
    public Guest checkInGuest(CheckInGuestDTO guestDataCheckIn) throws GuestNotFoundException, RoomNotFoundException {
        Guest guest = getGuestByRg(guestDataCheckIn.document());

        if(guest.getRoom() != null) {
            throw new RuntimeException("Hospede já está em um quarto");
        }

        Room room = roomService.getRoomByNumber(guestDataCheckIn.roomNumber());
        BeanUtils.copyProperties(guestDataCheckIn, guest);

        guest.setRoom(room);
        room.setGuest(guest);

        roomService.updateRoom(room);
        return guestRepository.save(guest);
    }

    private void verifyIfGuestExists(String rg) throws GuestNotFoundException {
        Guest guest = guestRepository.findByRg(rg);

        if(guest == null) {
            throw new GuestNotFoundException("Hospede não encontrado");
        }
    }
}

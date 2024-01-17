package br.com.hotel.domain.service;

import br.com.hotel.data.dto.guest.CheckInGuestDTO;
import br.com.hotel.data.dto.guest.CreateGuestDTO;
import br.com.hotel.data.model.guest.Guest;
import br.com.hotel.data.model.room.Room;
import br.com.hotel.domain.exceptions.guest.GuestAlreadyExistsException;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
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

    public Guest createGuest(CreateGuestDTO createGuestDTO) {
        Optional.ofNullable(guestRepository.findByRgOrCpf(createGuestDTO.rg(), createGuestDTO.document()))
        .ifPresent(existingGuest -> {
            try {
                throw new GuestAlreadyExistsException("Usuário já existe no sistema. ", existingGuest.getRg());
            } catch (GuestAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        });

        Guest guest = new Guest();
        BeanUtils.copyProperties(createGuestDTO, guest);
        return guestRepository.save(guest);
    }
    public Guest getGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = guestRepository.findByRg(rg);

        if(guest == null) {
            throw new GuestNotFoundException("Hospede não encontrado");
        }

        return guest;
    }

    /**
     * Deleta um hospede pelo rg
     * @param rg
     * @return String
     * @throws GuestNotFoundException
     */
    public String deleteGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = getGuestByRg(rg);
        guestRepository.delete(guest);
        return "Hospede deletado com sucesso";
    }

    // TODO refaz essa merda, não faz sentido
    public Guest updateGuest(String rg, Guest guest) {
        return guestRepository.updateGuestByRg(rg, guest);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Transactional
    public Guest checkInGuest(CheckInGuestDTO guestDataCheckIn) throws GuestNotFoundException {
        Guest guest = getGuestByRg(guestDataCheckIn.rg());

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
}

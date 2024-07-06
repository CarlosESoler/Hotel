package hotel.domain.service;

import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.Guest;
import hotel.domain.exceptions.guest.GuestAlreadyExistsExceptionWithRg;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.repository.GuestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class GuestService {
    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    /**
     * Create a new guest
     * @param createGuestDTO
     * @return Guest
     */
    public Guest createGuest(CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsExceptionWithRg {
        Optional<Guest> foundedGuest = guestRepository.findByRgOrDocument(createGuestDTO.rg(), createGuestDTO.document());

        if (foundedGuest.isPresent()) {
            throw new GuestAlreadyExistsExceptionWithRg(createGuestDTO.rg());
        }

        if(createGuestDTO.rg().length() != 12) {
            throw new IllegalArgumentException("RG deve conter 12 dígitos");
        }

        if(createGuestDTO.document().length() != 14) {
            throw new IllegalArgumentException("CPF deve conter 14 dígitos");
        }

        Guest newGuest = new Guest(createGuestDTO);
        newGuest.setDocument(createGuestDTO.document().replaceAll("[^0-9]", ""));
        newGuest.setRg(createGuestDTO.rg().replaceAll("[^0-9]", ""));
        return guestRepository.save(newGuest);
    }

    /**
     * Search and get a guest by rg
     *
     * @param rg
     * @return Guest
     * @throws GuestNotFoundException if the guest is not found
     */
    public Guest getGuestByRg(String rg) throws GuestNotFoundException {
        return guestRepository.findByRg(rg).orElseThrow(GuestNotFoundException::new);
    }

    /**
     * Delete a guest by rg
     *
     * @param rg
     * @return String
     * @throws GuestNotFoundException if the guest is not found
     */
    public String deleteGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = getGuestByRg(rg);
        guestRepository.delete(guest);
        return "Hospede deletado com sucesso";
    }

    /**
     * Get all guests
     *
     * @return List<Guest>
     */
    public List<Guest> getAllGuests() throws GuestNotFoundException {
        List<Guest> guests = guestRepository.findAll();

        if(guests.isEmpty()) {
            throw new GuestNotFoundException();
        }

        return guests;
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.saveAndFlush(guest);
    }

    public String deleteAllGuests() {
        guestRepository.deleteAll();
        return "Hospedes deletados com sucesso";
    }
}

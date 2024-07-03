package hotel.domain.service;

import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.guest.GuestAlreadyExistsException;
import br.com.hotel.domain.exceptions.guest.GuestNotFoundException;
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
     *
     * @param createGuestDTO
     * @return Guest
     */
    public Guest createGuest(CreateGuestDTO createGuestDTO) {
        Optional.ofNullable(guestRepository.findByRgOrDocument(createGuestDTO.rg(), createGuestDTO.document()))
                .ifPresent(existingGuest -> {
                    try {
                        throw new GuestAlreadyExistsException("Hospede já existe no sistema. ", existingGuest.getRg());
                    } catch (GuestAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }
                });
        Guest guest = new Guest(createGuestDTO);
        return guestRepository.save(guest);
    }

    /**
     * Search and get a guest by rg
     *
     * @param rg
     * @return Guest
     */
    public Guest getGuestByRg(String rg) throws GuestNotFoundException {
        Guest guest = guestRepository.findByRg(rg);
        if (guest == null) {
            throw new GuestNotFoundException("Hospede não encontrado");
        }
        return guest;
    }

    /**
     * Delete a guest by rg
     *
     * @param rg
     * @return String
     * @throws GuestNotFoundException
     */
    public String deleteGuestByRg(String rg) throws GuestNotFoundException {
        guestRepository.delete(getGuestByRg(rg));
        return "Hospede deletado com sucesso";
    }

    /**
     * Get all guests
     *
     * @return List<Guest>
     */
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.saveAndFlush(guest);
    }
}

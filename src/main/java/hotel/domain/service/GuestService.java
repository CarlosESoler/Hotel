package hotel.domain.service;

import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.guest.GuestAlreadyExistsWithRgException;
import hotel.domain.exceptions.guest.GuestNotFoundException;
import hotel.domain.repository.GuestRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class GuestService {
    private final GuestRepository guestRepository;
    private final PhoneService phoneService;
    private final AddressService addressService;
    private final CarService carService;

    public GuestService(GuestRepository guestRepository, PhoneService phoneService, AddressService addressService, CarService carService) {
        this.guestRepository = guestRepository;
        this.phoneService = phoneService;
        this.addressService = addressService;
        this.carService = carService;
    }

    /**
     * Create a new guest
     * @param createGuestDTO
     * @return Guest
     */
    public Guest createGuest(@NotNull CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsWithRgException {

        String formattedRg = formatRg(createGuestDTO.rg());
        String formattedDocument = formatDocument(createGuestDTO.document());

        Optional<Guest> foundedGuest = guestRepository.findByRg(formattedRg);

        if (foundedGuest.isPresent()) {
            throw new GuestAlreadyExistsWithRgException(formattedRg);
        }

        if(formattedRg.length() != 9) {
            throw new IllegalArgumentException("RG deve conter 12 dígitos");
        }

        if(formattedDocument.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 14 dígitos");
        }

        Guest newGuest = new Guest(createGuestDTO);
        newGuest.setDocument(formattedDocument);
        newGuest.setRg(formattedRg);
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


    private String formatRg(String rg) {
        return rg.replaceAll("[^0-9]", "");
    }

    private String formatDocument(String document) {
        return document.replaceAll("[^0-9]", "");
    }
}

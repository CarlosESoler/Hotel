package hotel.domain.service;

import hotel.data.dto.GuestDTO;
import hotel.data.entity.Address;
import hotel.data.entity.Guest;
import hotel.data.entity.Phone;
import hotel.domain.cache.CacheService;
import hotel.domain.repository.AddressRepository;
import hotel.domain.repository.GuestRepository;
import hotel.domain.repository.PhoneRepository;
import hotel.exceptions.guest.GuestAlreadyExistsWithRgException;
import hotel.exceptions.guest.GuestNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class GuestService {
    private final GuestRepository guestRepository;
    private final PhoneRepository phoneRepository;
    private final AddressRepository addressRepository;

    public GuestService(CacheService cacheService, GuestRepository guestRepository, PhoneRepository phoneRepository, AddressRepository addressRepository) {
        this.guestRepository = guestRepository;
        this.phoneRepository = phoneRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Create a new guest
     *
     * @param createGuestDTO
     * @return Guest
     */
    public Guest createGuest(@NotNull GuestDTO.CreateGuestDTO createGuestDTO) throws GuestAlreadyExistsWithRgException {

        String formattedRg = formatRg(createGuestDTO.rg());
        String formattedDocument = formatDocument(createGuestDTO.document());

        Optional<Guest> foundedGuest = guestRepository.findByRg(formattedRg);

        if (foundedGuest.isPresent())
            throw new GuestAlreadyExistsWithRgException(formattedRg);

        if (formattedRg.length() != 9)
            throw new IllegalArgumentException("RG deve conter 12 dígitos");

        if (formattedDocument.length() != 11)
            throw new IllegalArgumentException("CPF deve conter 14 dígitos");

        Guest newGuest = GuestDTO.CreateGuestDTO.toEntity(createGuestDTO);
        newGuest.setDocument(formattedDocument);
        newGuest.setRg(formattedRg);
        return guestRepository.save(newGuest);
    }

    public Guest addAddressToGuest(String rg, Address address) throws GuestNotFoundException {
        Guest guest = getGuestByRg(rg);
        address.setGuest(guest);
        return guestRepository.saveAndFlush(guest);
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
    @Cacheable(value = "guests")
    public List<GuestDTO.GetGuestDTO> getAllGuests() {
        final List<Guest> guests = new ArrayList<>(guestRepository.findAll());
        final List<GuestDTO.GetGuestDTO> guestsDTO = new ArrayList<>();
        for (Guest guest : guests) {
            List<Address> addresses = addressRepository.findByGuestId(guest.getId());
            List<Phone> phones = phoneRepository.findByGuestId(guest.getId());
            GuestDTO.GetGuestDTO guestDTO = GuestDTO.GetGuestDTO.toDTO(guest, addresses, phones);
            guestsDTO.add(guestDTO);
        }
        return guestsDTO;
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

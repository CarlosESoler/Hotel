import hotel.data.dto.guest.CreateGuestDTO;
import hotel.data.entity.guest.Guest;
import hotel.domain.exceptions.guest.GuestAlreadyExistsExceptionWithRg;
import hotel.domain.repository.GuestRepository;
import hotel.domain.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class GuestServiceTest {
    GuestRepository guestRepository;
    GuestService guestService = new GuestService(null);

    public void setUp() {
//        guestService = new GuestService(guestRepository);
//        createGuestDTO = new CreateGuestDTO("123456789",
//                "123456789",
//                LocalDate.now(),
//                "John",
//                "Doe",
//                "Jane",
//                "carlos");
//
//         guest = new Guest(createGuestDTO);
    }

    @Test
    public void createGuestWhenOtherGuestsAlreadyExists() {
        CreateGuestDTO createGuestDTO = new CreateGuestDTO("123456789",
                "123456789",
                LocalDate.now(),
                "John",
                "Doe",
                "Jane",
                "carlos");

        Assertions.assertThrows(GuestAlreadyExistsExceptionWithRg.class, () -> guestService.createGuest(createGuestDTO));
    }
}

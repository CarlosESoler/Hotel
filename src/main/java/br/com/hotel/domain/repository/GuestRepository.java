package br.com.hotel.domain.repository;

import br.com.hotel.data.model.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {

    Guest findByRg(String rg);
    Guest findByRgOrCpf(String rg, String cpf);
    Guest updateGuestByRg(String rg, Guest guest);

}

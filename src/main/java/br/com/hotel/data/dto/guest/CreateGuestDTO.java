package br.com.hotel.data.dto.guest;

import br.com.hotel.data.model.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateGuestDTO(
        String rg,
        String cpf,
        String carNumber,
        LocalDate birthDate,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        String fullName,
        String phone,
        String motherName,
        String address,
        String zipCode,
        String city,
        String state,
        String email
) {
}

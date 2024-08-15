package hotel.data.dto.hosting;

import hotel.data.entity.Hosting;

public record HostingDTO(
        String roomNumber,
        String guestRg
) {
    public static HostingDTO fromHosting(Hosting hosting) {
        return new HostingDTO(
                hosting.getRoom().getNumber(),
                hosting.getGuest().getRg()
        );
    }
//    public record GuestDTO(
//            String rg,
//            String document,
//            String name,
//            String lastName,
//            String motherName,
//            String email
//    ) {
//    }
}

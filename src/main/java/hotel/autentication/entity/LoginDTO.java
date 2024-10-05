package hotel.autentication.entity;

public record LoginDTO() {
    public record LoginRequest(String userName, String password) {
    }

    public record LoginResponse(String accessToken, Long expiresIn) {
    }
}

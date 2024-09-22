package hotel.autentication.entity;

public record Login() {
    public record LoginRequest(String username, String password) {
    }

    public record LoginResponse(String accessToken, Long expiresIn) {
    }
}

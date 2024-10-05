package hotel.autentication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "role_app")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public enum Values {
        ADMIN(1L), BASIC(2L);

        private final Long id;

        Values(Long id) {
            this.id = id;
        }
    }
}

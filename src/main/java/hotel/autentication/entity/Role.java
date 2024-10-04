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
        ADMIN(1L), USER(2L);

        private final Long roleId;

        Values(Long roleId) {
            this.roleId = roleId;
        }
    }
}

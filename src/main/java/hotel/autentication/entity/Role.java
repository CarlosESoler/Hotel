package hotel.autentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public enum Values {
        ADMIN(1L), USER(2L);

        private final Long id;

        Values(Long id) {
            this.id = id;
        }
    }
}

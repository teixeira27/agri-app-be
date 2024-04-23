package authentication.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="authentication")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorization_seq")
    @SequenceGenerator(name = "authorization_seq", sequenceName = "db.authorization_seq", allocationSize = 1)
    private Integer id;
    public String email;
    public String password;
}

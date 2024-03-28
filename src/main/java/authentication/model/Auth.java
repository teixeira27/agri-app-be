package authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "auth")
public class Auth {

    @Id
    @GeneratedValue
    private Integer id;
    public String username;
    public String password;
}

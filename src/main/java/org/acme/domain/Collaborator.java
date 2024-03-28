package org.acme.domain;

import io.quarkus.security.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@User
@Entity
@Table(name="collaborator")
public class Collaborator {

    @GeneratedValue
    @Id
    private int collaboratorID;
    private String username;
    private String password;
    private String name;
    private String role;

    @Override
    public String toString() {
        return "Collaborator{" +
                "collaboratorID=" + collaboratorID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

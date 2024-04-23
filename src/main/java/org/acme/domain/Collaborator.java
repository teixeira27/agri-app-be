package org.acme.domain;

import io.quarkus.security.User;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue
    private Integer collaboratorID;

    private String name;

    @OneToOne
    private Company company;

    private String role;

    //um collab apenas pode ter uma empresa
}

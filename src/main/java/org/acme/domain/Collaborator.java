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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collaborator_seq")
    @SequenceGenerator(name = "collaborator_seq", sequenceName = "db.collaborator_seq", allocationSize = 1)
    private Integer collaboratorID;

    private String name;

    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;

    private String role;

}

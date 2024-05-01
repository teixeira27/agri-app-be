package org.acme.domain;

import io.quarkus.security.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@User
@Entity
@Table(name = "collaborator")
public class Collaborator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collaborator_seq")
    @SequenceGenerator(name = "collaborator_seq", sequenceName = "db.collaborator_seq", allocationSize = 1)
    private Integer collaboratorId;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Company company;

    private String role;

}

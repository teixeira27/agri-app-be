package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "db.company_seq", allocationSize = 1)
    private Integer companyID;

    private String name;

    private long VAT;

    private int pin;

    @OneToMany(mappedBy = "company")
    private List<Collaborator> collaborators;

    @OneToMany(mappedBy = "company")
    private List<Land> lands;
}

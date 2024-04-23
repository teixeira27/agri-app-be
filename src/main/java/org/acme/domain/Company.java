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
    @GeneratedValue
    private Integer companyID;

    private String name;

    private long VAT;

    private int pin;

    @OneToMany
    private List<Collaborator> collaborators;
    //uma empresa pode ter vários colabs
}

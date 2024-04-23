package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_seq")
    @SequenceGenerator(name = "treatment_seq", sequenceName = "db.treatment_seq", allocationSize = 1)
    private Integer treatmentID;

    @OneToOne
    private Product product;

    private String description;

    private Date date;  //nao pode ser maior que currentDate

    private Integer securityDays;

    // historico de estados de uma cultura
    // sempre que se der update numa cultura é adicionado ao historico também
}

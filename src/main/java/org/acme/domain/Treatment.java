package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="treatment")
public class Treatment {

    @GeneratedValue
    @Id
    private Integer treatmentID;

    @Transient
    private Product product;

    private String description;
    // private Date - nao pode ser maior que currentDate
    // private security Days - intervalo de segurança para colheita

    // historico de estados de uma cultura
    // sempre que se der update numa cultura é adicionado ao historico também
}

package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "treatment")
public class Treatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_seq")
    @SequenceGenerator(name = "treatment_seq", sequenceName = "db.treatment_seq", allocationSize = 1)
    private Integer treatmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private String description;
    private Date date;
    private Integer securityDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cultureId")
    private Culture culture;

}

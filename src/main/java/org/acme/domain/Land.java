package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="land")
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "land_seq")
    @SequenceGenerator(name = "land_seq", sequenceName = "db.land_seq", allocationSize = 1)
    private Integer landID;

    private String name;

    private int area;

    private String coordinates;

    private String localization;

    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;
}

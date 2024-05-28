package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "land")
public class Land implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "land_seq")
    @SequenceGenerator(name = "land_seq", sequenceName = "db.land_seq", allocationSize = 1)
    private Integer landId;

    private String name;
    private Integer area;
    private Double latitude;
    private Double longitude;
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "land", fetch = FetchType.EAGER)
    private List<Culture> cultures;
}

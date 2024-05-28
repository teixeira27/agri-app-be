package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "culture")
public class Culture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "culture_seq")
    @SequenceGenerator(name = "culture_seq", sequenceName = "db.culture_seq", allocationSize = 1)
    private Integer cultureId;

    private String name;
    private String description;

    @Column(name = "date")
    private Date creation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "landId")
    private Land land;

    @OneToMany(mappedBy = "culture", fetch = FetchType.EAGER)
    private List<Treatment> treatments;

}

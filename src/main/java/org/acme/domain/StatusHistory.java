package org.acme.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "status_history")
public class StatusHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_history_seq")
    @SequenceGenerator(name = "status_history_seq", sequenceName = "db.status_history_seq", allocationSize = 1)
    private Integer statusHistoryId;

    private Instant date;
    private String description;

    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cultureId")
    private Culture culture;
}

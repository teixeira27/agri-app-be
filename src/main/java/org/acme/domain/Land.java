package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="field")
public class Land {
    @Id
    @GeneratedValue
    private Integer fieldID;

    private String name;

    private int area;

    private String coordinates;

    private String localization;
}

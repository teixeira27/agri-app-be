package org.acme.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
public class Product {

    @GeneratedValue
    @Id
    private Integer productID;

    private String name;

    private String safetyPeriod;

    private String description;

    private String type;
}

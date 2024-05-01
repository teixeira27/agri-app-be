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
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "db.product_seq", allocationSize = 1)
    private Integer productId;

    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Treatment> treatments;
}

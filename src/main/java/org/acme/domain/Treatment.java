package org.acme.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="treatment")
public class Treatment {

    @GeneratedValue
    @Id
    private int treatmentID;
    @Transient
    private Product product;
    private String description;

}

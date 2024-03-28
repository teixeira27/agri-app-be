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
    private int treatmentID;
    @Transient
    private Product product;
    private String description;

    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentID=" + treatmentID +
                ", product=" + product +
                ", description='" + description + '\'' +
                '}';
    }
}

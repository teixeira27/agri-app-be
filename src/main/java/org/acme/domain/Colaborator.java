package org.acme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="colaborator")
public class Colaborator {

    @GeneratedValue
    @Id
    private int colaboratorID;
    private String name;
    private String role;

    @Override
    public String toString() {
        return "Colaborator{" +
                "colaboratorID=" + colaboratorID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

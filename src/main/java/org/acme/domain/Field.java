package org.acme.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="field")
public class Field {
    @Id
    @GeneratedValue
    private int fieldID;
    private String name;
    private int area;
    private String coordinates;
    private String localization;

    @Override
    public String toString() {
        return "Field{" +
                "fieldID=" + fieldID +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", coordinates='" + coordinates + '\'' +
                ", localization='" + localization + '\'' +
                '}';
    }
}

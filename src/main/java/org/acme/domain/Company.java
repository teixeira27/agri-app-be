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
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue
    private int companyID;
    private String name;
    private long VAT;
    private String address;

    @Override
    public String toString() {
        return "Company{" +
                "companyID=" + companyID +
                ", name='" + name + '\'' +
                ", VAT=" + VAT +
                ", address='" + address + '\'' +
                '}';
    }
}

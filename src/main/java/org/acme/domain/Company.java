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
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue
    private int companyID;
    private String name;
    private long VAT;
    private String address;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVAT() {
        return VAT;
    }

    public void setVAT(long VAT) {
        this.VAT = VAT;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

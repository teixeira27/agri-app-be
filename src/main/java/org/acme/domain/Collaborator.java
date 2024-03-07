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
@Table(name="collaborator")
public class Collaborator {

    @GeneratedValue
    @Id
    private int collaboratorID;
    private String name;
    private String role;

    public int getCollaboratorID() {
        return collaboratorID;
    }

    public void setCollaboratorID(int collaboratorID) {
        this.collaboratorID = collaboratorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "collaboratorID=" + collaboratorID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

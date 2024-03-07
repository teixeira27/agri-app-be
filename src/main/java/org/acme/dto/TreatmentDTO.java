package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.domain.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentDTO {
    private int treatmentID;
    private Product product;
    private String description;
}

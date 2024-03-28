package org.acme.dto;

import lombok.*;
import org.acme.domain.Product;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreatmentDTO {
    private int treatmentID;
    private Product product;
    private String description;
}

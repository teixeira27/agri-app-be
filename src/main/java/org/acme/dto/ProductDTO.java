package org.acme.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private int productID;
    private String name;
    private String safetyPeriod;
    private String description;
    private String type;
}

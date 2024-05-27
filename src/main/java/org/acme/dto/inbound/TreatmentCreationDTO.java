package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreatmentCreationDTO {
    private String productName;
    private String description;
    private Integer securityDays;
    private Integer cultureId;
}

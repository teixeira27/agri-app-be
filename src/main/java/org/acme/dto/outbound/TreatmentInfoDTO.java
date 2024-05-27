package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreatmentInfoDTO {
    private String productName;
    private String description;
    private String date;
    private Integer securityDays;
    private Integer treatmentId;
}

package org.acme.dto.outbound;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TreatmentInfoDTO {
    private String productName;
    private String description;
    private Date date;
    private Integer securityDays;
    private Integer treatmentId;
}

package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyCreationDTO {
    private String name;
    private long vat;
    private int pin;
    private Integer collaboratorId;
}

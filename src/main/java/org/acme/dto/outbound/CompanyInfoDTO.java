package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyInfoDTO {
    private String name;
    private long vat;
    private Integer companyId;
}

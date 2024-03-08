package org.acme.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO {
    private int companyID;
    private String name;
    private long VAT;
    private String address;
}

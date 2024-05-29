package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollaboratorDetailsDTO {
    private String name;
    private String email;
    private String companyName;
    private long Vat;
}

package org.acme.dto.inbound;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LandCreationDTO {
    private String name;
    private Integer area;
    private Integer companyId;
}

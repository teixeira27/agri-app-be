package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CultureCreationDTO {
    private String name;
    private String description;
    private Integer landId;
}

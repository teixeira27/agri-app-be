package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LandInfoDTO {
    private Integer landId;
    private String name;
    private String location;
}

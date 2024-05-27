package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LandCoordinatesDTO {
    private Integer landId;
    private Double latitude;
    private Double longitude;
}

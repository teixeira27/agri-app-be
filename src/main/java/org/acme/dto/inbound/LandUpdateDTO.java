package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LandUpdateDTO {
    private Integer landId;
    private Double latitude;
    private Double longitude;
}

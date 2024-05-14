package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusHistoryCreationDTO {
    private Integer cultureId;
    private String description;
    private byte[] image;
}

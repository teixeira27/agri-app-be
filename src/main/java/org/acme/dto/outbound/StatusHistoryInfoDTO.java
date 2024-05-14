package org.acme.dto.outbound;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusHistoryInfoDTO {
    private Integer statusHistoryId;
    private Instant date;
    private String description;
    private byte[] image;
}

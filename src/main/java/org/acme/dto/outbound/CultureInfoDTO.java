package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CultureInfoDTO {
    private Integer cultureId;
    private String name;
    private String description;
    private String creationDate;
}

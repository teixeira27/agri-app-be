package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailVerificationDTO {
    private String pin;
    private Integer collaboratorId;
}

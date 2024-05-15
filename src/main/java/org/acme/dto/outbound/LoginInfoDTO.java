package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginInfoDTO {
    private String token;
    private Integer collaboratorId;
}

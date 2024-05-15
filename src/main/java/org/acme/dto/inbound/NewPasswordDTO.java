package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewPasswordDTO {
    private String newPassword;
    private String pin;
    private Integer collaboratorId;
}

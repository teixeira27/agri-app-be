package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResetPasswordDTO {
    private String email;
}

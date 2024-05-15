package org.acme.dto.inbound;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollaboratorLoginDTO {
    private String email;
    private String password;
}

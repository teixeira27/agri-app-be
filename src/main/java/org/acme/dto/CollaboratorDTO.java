package org.acme.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollaboratorDTO {
    private int collaboratorID;
    private String name;
    private String role;
}

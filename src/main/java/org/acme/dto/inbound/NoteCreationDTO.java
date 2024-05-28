package org.acme.dto.inbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteCreationDTO {
    private String title;
    private String description;
    private Integer collaboratorId;
    private Integer companyId;
}

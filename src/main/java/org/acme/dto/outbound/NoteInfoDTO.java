package org.acme.dto.outbound;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteInfoDTO {
    private Integer noteId;
    private String title;
    private String description;
    private String collaboratorName;
    private String lastUpdated;
}

package org.acme.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldDTO {
    private int fieldID;
    private String name;
    private int area;
    private String coordinates;
    private String localization;
}

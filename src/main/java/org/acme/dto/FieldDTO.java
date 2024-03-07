package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
    private int fieldID;
    private String name;
    private int area;
    private String coordinates;
    private String localization;
}

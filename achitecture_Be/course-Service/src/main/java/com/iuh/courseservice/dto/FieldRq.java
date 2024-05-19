package com.iuh.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FieldRq implements Serializable {
    public int fieldId;
    public String fieldName;
}

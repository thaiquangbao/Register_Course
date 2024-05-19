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
public class ClassCourseRp implements Serializable {
    private static final long serialVersionUID = 297063413780202219L;
    private int idClass;
    private String nameClass;
    private int courseId;



}

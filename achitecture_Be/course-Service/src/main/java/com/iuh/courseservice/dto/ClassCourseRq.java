package com.iuh.courseservice.dto;

import com.iuh.courseservice.model.Course;
import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassCourseRq implements Serializable {
    private int idClass;
    private String nameClass;
    private int courseId;


}

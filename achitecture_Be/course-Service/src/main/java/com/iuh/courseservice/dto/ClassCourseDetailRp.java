package com.iuh.courseservice.dto;

import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.model.Lecturer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassCourseDetailRp implements Serializable {
    private static final long serialVersionUID = 297063413780202219L;
    private int idClassCourseDetail;
    private String ngayHoc;
    private LocalTime timeHoc;
    private LocalTime timeEnd;
    private int siSo;
    private int soLuongDaDangKy;
    private int lecturer;

//    public int classCourse;

}

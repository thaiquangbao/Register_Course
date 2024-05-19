package com.iuh.courseservice.dto;

import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.model.Lecturer;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassCourseDetailRq {
    private String ngayHoc;
    private LocalTime timeHoc;
    private LocalTime timeEnd;
    private int siSo;
    private int soLuongDaDangKy;
    private int classCourseId;
    private int lecturer;

}

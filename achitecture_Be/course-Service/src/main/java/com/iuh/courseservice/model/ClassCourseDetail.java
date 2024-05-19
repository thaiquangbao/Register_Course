package com.iuh.courseservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Builder
@Table(name = "class_course_detail")
public class ClassCourseDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCourseDetail")
    private int idClassCourseDetail;
    private String ngayHoc;
    private LocalTime timeHoc;
    private LocalTime timeEnd;
    private int siSo;
    private int soLuongDaDangKy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_detail_id")
    private ClassCourse classCourse;





}

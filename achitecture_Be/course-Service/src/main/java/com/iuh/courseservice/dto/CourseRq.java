package com.iuh.courseservice.dto;

import com.iuh.courseservice.model.ClassCourse;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseRq implements Serializable {
    public  long maMonHoc;
    public String tenMonHoc;
    public boolean trangThai;
    public boolean batBuoc;
    public int soTinChi;
    private Set<Integer> listCoursePrev;
}

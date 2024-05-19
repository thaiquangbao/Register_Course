package com.iuh.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Builder
@Table(name = "lecturer")
public class Lecturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int giangVienid;
    public String tenGiangVien;
    public String chuyenNghanh;

    @OneToMany(mappedBy = "lecturer",cascade = CascadeType.ALL)
    public Set<ClassCourseDetail> classCourseDetail;


    public Lecturer(int giangVienid) {
        this.giangVienid = giangVienid;
    }
}

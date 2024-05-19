package com.iuh.courseservice.service;

import com.iuh.courseservice.dto.CourseRp;
import com.iuh.courseservice.dto.CourseRq;
import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.repository.CourseRepo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    //    public  long maMonHoc;
//    public String tenMonHoc;
//    public boolean trangthai;
//    public boolean batbuoc;
//    public int soTinChi;
    @Autowired
    private CourseRepo courseRepo;
    private EntityManager entityManager;

    public CourseRq addNewCourse(CourseRq courserq){
        Course course = Course.builder()
                .tenMonHoc(courserq.getTenMonHoc())
                .soTinChi(courserq.getSoTinChi())
                .trangThai(courserq.isTrangThai())
                .batBuoc(courserq.isBatBuoc())
                .build();
        courseRepo.save(course);
        return courserq;
    }
    public List<CourseRp> getallCourse(){
        List<CourseRp> list = courseRepo.findAll().stream().map(this::mapCourseToCourseRespone).toList();
//        System.out.println(list);
        return courseRepo.findAll().stream().map(this::mapCourseToCourseRespone).toList();
    }

    public CourseRp getCourseById(long id){
        Optional<Course> courseOptional =  courseRepo.findById(id);
        CourseRp courseRp = null;
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            courseRp = CourseRp
                    .builder()
                    .id(course.getMaMonHoc())
                    .tenMonHoc(course.getTenMonHoc())
                    .soTinChi(course.getSoTinChi())
                    .trangThai(course.isTrangThai())
                    .batBuoc(course.isBatBuoc())
                    .build();
        }
        return courseRp;
    }
    public CourseRp mapCourseToCourseRespone(Course course){
        return CourseRp.builder()
                .id(course.getMaMonHoc())
                .tenMonHoc(course.getTenMonHoc())
                .soTinChi(course.getSoTinChi())
                .trangThai(course.isTrangThai())
                .batBuoc(course.isBatBuoc())
                .listCoursePrev(course.getListCoursePrev())
                .build();
    }


}


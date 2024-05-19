package com.iuh.courseservice.service;

import com.iuh.courseservice.dto.ClassCourseRp;
import com.iuh.courseservice.dto.ClassCourseRq;
import com.iuh.courseservice.model.ClassCourse;
import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.repository.ClassCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassCourseService {
    @Autowired
    public ClassCourseRepo classcourseRepo;


    public ClassCourseRq addClassCourse(ClassCourseRq courseRq){
        ClassCourse course = ClassCourse.builder()
                .nameClass(courseRq.getNameClass())
                .courseId(new Course(courseRq.getCourseId(),"",false,false,0,null,null))
                .build();
        classcourseRepo.save(course);
        return courseRq;
    }
    public ClassCourseRp getClassCourseById(int id){
        Optional<ClassCourse> opcourse = classcourseRepo.findById(id);
        if (opcourse.isPresent()){
            ClassCourse course = opcourse.get();
            return ClassCourseRp.
                    builder()
                    .idClass(course.getIdClass())
                    .nameClass(course.getNameClass())
                    .build();
        }
        return null;
    }

    public List<ClassCourseRp> findAllClassCourse(){
        return classcourseRepo.findAll().stream().map(this::mapOneToOne).toList();
    }

    public List<ClassCourseRp> findAllClassCourseByCourseId(Course course){
        return classcourseRepo.findAllByCourseId(course).stream().map(this::mapOneToOne).toList();
    }

    public ClassCourseRp mapOneToOne(ClassCourse classCourse){
       return ClassCourseRp.
               builder()
               .idClass(classCourse.getIdClass())
               .nameClass(classCourse.getNameClass())
               .build();
    }
}

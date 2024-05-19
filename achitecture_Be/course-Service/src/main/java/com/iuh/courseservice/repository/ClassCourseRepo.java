package com.iuh.courseservice.repository;

import com.iuh.courseservice.model.ClassCourse;
import com.iuh.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassCourseRepo extends JpaRepository<ClassCourse,Integer> {
    public List<ClassCourse> findAllByCourseId(Course courseId);
}

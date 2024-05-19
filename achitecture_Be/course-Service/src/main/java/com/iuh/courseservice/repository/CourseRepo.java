package com.iuh.courseservice.repository;

import com.iuh.courseservice.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;

public interface CourseRepo extends JpaRepository<Course,Long> {
}

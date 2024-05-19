package com.iuh.courseservice.controller;

import com.iuh.courseservice.dto.ClassCourseDetailRp;
import com.iuh.courseservice.dto.ClassCourseDetailRq;
import com.iuh.courseservice.dto.ClassCourseRp;
import com.iuh.courseservice.dto.ClassCourseRq;
import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.service.ClassCourseDetailService;
import com.iuh.courseservice.service.ClassCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ClassCourse")
public class ClassCourseController {
    @Autowired
    public ClassCourseService classCourseService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ClassCourseRp>> getAllClassCourseRp(){
        return new ResponseEntity<>(classCourseService.findAllClassCourse(), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ClassCourseRp> getClassCourseRpById(@PathVariable int id){
        return new ResponseEntity<>(classCourseService.getClassCourseById(id),HttpStatus.OK);
    }
//    findAllClassCourseByCourseId
    @PostMapping("/findAllClassCourseByCourseId")
    public ResponseEntity<List<ClassCourseRp>> getAllClassCourseByCourseId(@RequestBody Course course){
        return new ResponseEntity<>(classCourseService.findAllClassCourseByCourseId(course), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ClassCourseRq> addClassCourseDetail(@RequestBody ClassCourseRq classCourseRq){
        return new ResponseEntity<>(classCourseService.addClassCourse(classCourseRq),HttpStatus.OK);
    }
}

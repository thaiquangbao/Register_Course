package com.iuh.courseservice.controller;

import com.iuh.courseservice.dto.ClassCourseRp;
import com.iuh.courseservice.dto.ClassCourseRq;
import com.iuh.courseservice.dto.CourseRp;
import com.iuh.courseservice.dto.CourseRq;
import com.iuh.courseservice.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Resource(name = "getTemplateCourse")
    private HashOperations<String,String, CourseRp> CourseHashOperations;

    @GetMapping("/findOne/{id}")
    public ResponseEntity<CourseRp> getCourseById(@PathVariable int id){
        CourseRp rs = CourseHashOperations.get("courseRp", String.valueOf(id));
        System.out.println(rs);
        if (rs == null) {
            rs = courseService.getCourseById(id);
            CourseHashOperations.put("courseRp",String.valueOf(id), rs);
            System.out.println("find without redis "+rs);
        }

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<CourseRp>> getAllCourse(@RequestHeader("loggedUser") String username){
        return new ResponseEntity<>(courseService.getallCourse(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CourseRq> addclassCourse(@RequestBody CourseRq courserq){
        return new ResponseEntity<>(courseService.addNewCourse(courserq),HttpStatus.OK);
    }
    //@requestHeader loggedUser

}

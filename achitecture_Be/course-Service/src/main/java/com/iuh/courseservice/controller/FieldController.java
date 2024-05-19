package com.iuh.courseservice.controller;

import com.iuh.courseservice.dto.ClassCourseDetailRp;
import com.iuh.courseservice.dto.ClassCourseDetailRq;
import com.iuh.courseservice.dto.FieldRp;
import com.iuh.courseservice.dto.FieldRq;
import com.iuh.courseservice.service.ClassCourseDetailService;
import com.iuh.courseservice.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Field")
public class FieldController {
    @Autowired
    public FieldService fieldService;

    @GetMapping("/findAll")
    public ResponseEntity<List<FieldRp>> getAllFieldRp(){
        return new ResponseEntity<>(fieldService.getAllField(), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<FieldRp> getClassCourseDetailRpById(@PathVariable int id){
        return new ResponseEntity<>(fieldService.getOneById(id),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<FieldRq> addclassCourseDetail(@RequestBody FieldRq fieldRq){
        return new ResponseEntity<>(fieldService.addNewField(fieldRq),HttpStatus.OK);
    }
}

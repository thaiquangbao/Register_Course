package com.iuh.courseservice.controller;

import com.iuh.courseservice.dto.FieldRp;
import com.iuh.courseservice.dto.FieldRq;
import com.iuh.courseservice.dto.LecturerRp;
import com.iuh.courseservice.dto.LecturerRq;
import com.iuh.courseservice.service.FieldService;
import com.iuh.courseservice.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {
    @Autowired
    public LecturerService lecturerService;

    @GetMapping("/findAll")
    public ResponseEntity<List<LecturerRp>> getAllFieldRp(){
        return new ResponseEntity<>(lecturerService.getAllLecturer(), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<LecturerRp> getClassCourseDetailRpById(@PathVariable int id){
        return new ResponseEntity<>(lecturerService.getOneById(id),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<LecturerRq> addNewLecturer(@RequestBody  LecturerRq lecturerRq){
        return new ResponseEntity<>(lecturerService.addNewLectrer(lecturerRq),HttpStatus.OK);
    }
}

package com.iuh.registerservice.controller;


import com.iuh.registerservice.dto.RegisterRqRp;
import com.iuh.registerservice.service.RegisterService;
import jakarta.ws.rs.POST;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    public RegisterService registerService;

    @PostMapping("/add")
    public ResponseEntity<RegisterRqRp> addNewRegister(@RequestBody RegisterRqRp registerRqRp,@RequestHeader("loggedUser") String loggedUser){
        registerRqRp.setUserName(loggedUser);
        return new ResponseEntity<>(registerService.addNewRegister(registerRqRp), HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<RegisterRqRp> removeRegister(@RequestBody RegisterRqRp registerRqRp){
        return new ResponseEntity<>(registerService.removeRegister(registerRqRp), HttpStatus.OK);
    }
    @GetMapping("/getByUserName")
    public ResponseEntity<List<RegisterRqRp>> getCourseByUserName(@RequestHeader("loggedUser") String loggedUser){
        System.out.println(loggedUser);
        return new ResponseEntity<>(registerService.getListRegisterByUserName(loggedUser), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<RegisterRqRp>> getAllRegister(@PathVariable String username){
        return new ResponseEntity<>(registerService.getListRegisterByUserName(username), HttpStatus.OK);
    }
    @GetMapping("/getRegister/{id}")
    public ResponseEntity<List<RegisterRqRp>> getCourseByUserNameAndHocKy(@RequestHeader("loggedUser") String loggedUser,@PathVariable int id){
        return new ResponseEntity<>(registerService.getListRegisterByUserNameAndHocKyDangKy(loggedUser,id), HttpStatus.OK);
    }
}

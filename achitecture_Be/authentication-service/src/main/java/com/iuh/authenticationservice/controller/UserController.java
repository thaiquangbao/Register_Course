package com.iuh.authenticationservice.controller;

import com.iuh.authenticationservice.dto.ReqRes;
import com.iuh.authenticationservice.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    public UserManageService userManageService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> registerUser(@RequestBody ReqRes reqRes){
        System.out.println(reqRes);
        return  ResponseEntity.ok(userManageService.register(reqRes));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> loginUser(@RequestBody ReqRes reqRes){
        return  ResponseEntity.ok(userManageService.login(reqRes));
    }
    @PostMapping("/auth/refreshToken")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes reqRes){
        return  ResponseEntity.ok(userManageService.refreshToken(reqRes));
    }

    @GetMapping("/GV/getAllSv")
    public ResponseEntity<ReqRes> getAllSV(@RequestBody ReqRes reqRes){
        return  ResponseEntity.ok(userManageService.getAllUser());
    }
    @GetMapping("/GV/getSVById/{id}")
    public ResponseEntity<ReqRes> getSVById(@PathVariable int id){
        return  ResponseEntity.ok(userManageService.getOneUserById(id));
    }
    @GetMapping("/GV/deleteSV/{id}")
    public ResponseEntity<ReqRes> deleteSV(@PathVariable int id){
        return  ResponseEntity.ok(userManageService.deleteUserById(id));
    }

}

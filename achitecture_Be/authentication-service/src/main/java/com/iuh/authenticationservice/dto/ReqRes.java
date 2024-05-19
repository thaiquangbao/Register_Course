package com.iuh.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.iuh.authenticationservice.model.User;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String userName;
    private String fullname;
    private boolean gender;
    private String lopHP;
    private String email;
    private String address;
    private String role;
    private String password;
    private String city;
    private User user;
    private List<User> listUser;
}

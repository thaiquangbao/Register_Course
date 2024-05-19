package com.iuh.authenticationservice.service;

import com.iuh.authenticationservice.dto.ReqRes;
import com.iuh.authenticationservice.model.User;
import com.iuh.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManageService {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

        public ReqRes register(ReqRes reqRes){
            ReqRes reqRes1 = new ReqRes();
            try {
                User user = new User();
                user.setUserName(reqRes.getUserName());
                user.setPassword(passwordEncoder.encode(reqRes.getPassword()));
                user.setAddress(reqRes.getCity());
                user.setRole(reqRes.getRole());
                user.setLopHP(reqRes.getLopHP());
                user.setFullName(reqRes.getFullname());
                user.setGender(reqRes.isGender());
                Optional<User> u = userRepository.findByUserName(reqRes.getUserName());
                if (u.isPresent()){
                    reqRes1.setStatusCode(500);
                    reqRes1.setError("user exist");
                    return reqRes1;
                }
                User userResult = userRepository.save(user);
                if (userResult.getId()>0){
                    reqRes1.setUser(userResult);
                    reqRes1.setMessage("save success");
                    reqRes1.setStatusCode(200);
                }
            }catch (Exception e){
                reqRes1.setStatusCode(500);
                reqRes1.setError(e.getMessage());
            }

            return  reqRes1;
        }
    public ReqRes login(ReqRes loginreqRes){
        ReqRes reqRes = new ReqRes();
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginreqRes.getUserName(),
                                    loginreqRes.getPassword()));
            var user = userRepository
                    .findByUserName(
                            loginreqRes.getUserName())
                    .orElseThrow();
            String jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),user);
            reqRes.setAddress(user.getAddress());
            reqRes.setRole(user.getRole());
            reqRes.setLopHP(user.getLopHP());
            reqRes.setFullname(user.getFullName());
            reqRes.setGender(user.isGender());
            reqRes.setStatusCode(200);
            reqRes.setToken(jwt);
            reqRes.setRefreshToken(refreshToken);
            reqRes.setExpirationTime("24Hr");
            reqRes.setMessage("Login success");
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setError(e.getMessage());
        }
        return  reqRes;
    }
    public ReqRes refreshToken(ReqRes refreshReqRes){
            ReqRes reqRes = new ReqRes();
            try {
                String userName = jwtUtils.extractUserName(refreshReqRes.getToken());
                User user = userRepository.findByUserName(userName).orElseThrow();
                if (jwtUtils.isTokenvalid(refreshReqRes.getToken(),user)){
                    reqRes.setStatusCode(200);
                    var jwt = jwtUtils.generateToken(user);
                    reqRes.setToken(jwt);
                    reqRes.setRefreshToken(refreshReqRes.getToken());
                    reqRes.setExpirationTime("24Hr");
                    reqRes.setMessage("refresh Token success");

                }
            }catch (Exception e){
                reqRes.setStatusCode(500);
                reqRes.setMessage(e.getMessage());
            }
        return reqRes;
    }

    public ReqRes getAllUser(){
            ReqRes reqRes = new ReqRes();
            try {
                List<User> list = userRepository.findAll();
                if (!list.isEmpty()) {
                    reqRes.setListUser(list);
                    reqRes.setStatusCode(200);
                    reqRes.setMessage("get List success");
                }else {
                    reqRes.setStatusCode(404);
                    reqRes.setMessage("not find");
                }

            }catch (Exception e){
                reqRes.setStatusCode(500);
                reqRes.setMessage(e.getMessage());
            }
            return reqRes;
    }
    public ReqRes getOneUserById(int userId){
        ReqRes reqRes = new ReqRes();
        try {
            User list = userRepository.findById(userId).orElseThrow();

                reqRes.setUser(list);
                reqRes.setStatusCode(200);
                reqRes.setMessage("get user "+userId+" success");
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage(e.getMessage());
        }
        return reqRes;
    }
    public ReqRes deleteUserById(int userId){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> list = userRepository.findById(userId);
            if (list != null) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("delete user "+userId+" success");
            }else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("not found");
            }
        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage(e.getMessage());
        }
        return reqRes;
    }
    public ReqRes updateUser(int idUser,ReqRes updateReqRes){
        ReqRes reqRes = new ReqRes();

        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()){
           User user = optionalUser.get();
            user.setUserName(updateReqRes.getUserName());
            user.setAddress(updateReqRes.getCity());
            user.setPassword(passwordEncoder.encode(updateReqRes.getUserName()));
            userRepository.save(user
            );
            reqRes.setMessage("Success");
            reqRes.setStatusCode(200);
        }
        return reqRes;
       }
    public ReqRes getUserByUserName(String idUser,ReqRes updateReqRes){
        ReqRes reqRes = new ReqRes();
        Optional<User> optionalUser = userRepository.findByUserName(idUser);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            reqRes.setUserName(updateReqRes.getUserName());
            reqRes.setCity(updateReqRes.getCity());
            reqRes.setPassword(passwordEncoder.encode(updateReqRes.getUserName()));
            reqRes.setMessage("Success");
            reqRes.setStatusCode(200);
        }
        return reqRes;
    }

}

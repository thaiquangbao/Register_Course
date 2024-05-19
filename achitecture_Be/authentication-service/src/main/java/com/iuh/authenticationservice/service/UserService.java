package com.iuh.authenticationservice.service;

import com.iuh.authenticationservice.model.User;
import com.iuh.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow();
    }
//    List<User> store = new ArrayList<>;
//    public UserService(){
//        store.add(new User(UUID.randomUUID().toString(),"QuangToan","pass1","GV"));
//        store.add(new User(UUID.randomUUID().toString(),"Quang","pass1","SV"));
//        store.add(new User(UUID.randomUUID().toString(),"Toan","pass1","SV"));
//        store.add(new User(UUID.randomUUID().toString(),"QToan","pass1","SV"));
//    }
}

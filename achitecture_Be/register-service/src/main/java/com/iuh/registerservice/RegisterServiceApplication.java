package com.iuh.registerservice;

import com.iuh.registerservice.dto.RegisterRqRp;
import com.iuh.registerservice.model.Register;
import com.iuh.registerservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class RegisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterServiceApplication.class, args);
    }

//    @Autowired
//    public RegisterService registerService;
//    @Override
//    public void run(String... args) throws Exception {
//        List<RegisterRqRp> registerRqRp = registerService.getListRegisterByUserName("20080709");
//        for (RegisterRqRp child : registerRqRp){
//            System.out.println(child);
//        }
//        System.out.println(registerRqRp);
//        for (int i = 1; i < 2; i++) {
//            RegisterRqRp register = new RegisterRqRp(i,"200807091",1,1,true);
//            registerService.addNewRegister(register);
//        }
//    }
}

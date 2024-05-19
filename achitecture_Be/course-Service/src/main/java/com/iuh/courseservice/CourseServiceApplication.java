package com.iuh.courseservice;

import com.github.javafaker.Faker;
import com.iuh.courseservice.dto.ClassCourseRq;
import com.iuh.courseservice.dto.CourseRp;
import com.iuh.courseservice.dto.CourseRq;
import com.iuh.courseservice.model.ClassCourse;
import com.iuh.courseservice.model.Course;
import com.iuh.courseservice.repository.ClassCourseRepo;
import com.iuh.courseservice.repository.CourseRepo;

import com.iuh.courseservice.service.ClassCourseService;
import com.iuh.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CourseServiceApplication.class, args);
    }
}

//    @Autowired
//    public CourseRepo courseRepo;
//    @Autowired
//    public ClassCourseService classcourseService;
//    @Override
//    public void run(String... args) throws Exception {

//        for (int i = 0; i < 5; i++) {
//            classCourseRepo.save(new ClassCourse(i,"DHKTPM"+i+"TT",true,faker.number().numberBetween(1,3),null));
//            classcourseService.addClassCourse(new ClassCourseRq(i,faker.name().name(),4));

//        }
//        for (int i = 0; i < 10; i++) {
//            Faker faker = new Faker();
//            courseRepo.save(new Course(i,faker.book().genre(),true,true,faker.number().numberBetween(2,3),null));
//        }
//    }


//}

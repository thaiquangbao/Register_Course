package com.iuh.courseservice.Configuration;

import com.iuh.courseservice.dto.ClassCourseDetailRp;
import com.iuh.courseservice.dto.ClassCourseRp;
import com.iuh.courseservice.dto.CourseRp;
import com.iuh.courseservice.model.ClassCourseDetail;
import com.iuh.courseservice.repository.ClassCourseDetailRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        return jedis; 
    }

    @Bean
    public RedisTemplate<String, ClassCourseDetailRp> getTemplateClassCourseDetail(){
        RedisTemplate<String, ClassCourseDetailRp> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, CourseRp> getTemplateCourse(){
        RedisTemplate<String, CourseRp> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        return redisTemplate;
    }
    @Bean
    public RedisTemplate<String, ClassCourseRp> getTemplateClassCourse(){
        RedisTemplate<String, ClassCourseRp> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnectionFactory());
        return redisTemplate;
    }

}

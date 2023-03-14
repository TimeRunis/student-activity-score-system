package com.sass.studentactivityscoresystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sass.studentactivityscoresystem.mapper")
public class StudentActivityScoreSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentActivityScoreSystemApplication.class, args);
    }

}

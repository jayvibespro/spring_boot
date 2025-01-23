package com.jaycode.demo.features.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner courseCommandLineRunner(CourseRepository repository){
        return  args -> {
            Course maths =   new Course(
                    "Mathematics",
                    "MT100"
            );

            Course english =   new Course(
                    "English",
                    "EN101"
            );
            Course history =   new Course(
                    "History",
                    "HS103"
            );

            repository.saveAll(
                    List.of(maths, english, history)
            );
        };
    }
}

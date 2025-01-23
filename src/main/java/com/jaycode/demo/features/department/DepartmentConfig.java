package com.jaycode.demo.features.department;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfig {

    @Bean
    CommandLineRunner departmentCommandLineRunner(DepartmentRepository repository){
        return  args -> {
            Department maths =   new Department(
                    "Mathematics Department"
            );

            Department socials =   new Department(
                    "Social Studies Department"
            );

            Department language =   new Department(
                    "Language Department"
            );


            repository.saveAll(
                    List.of(maths, socials, language)
            );
        };
    }
}

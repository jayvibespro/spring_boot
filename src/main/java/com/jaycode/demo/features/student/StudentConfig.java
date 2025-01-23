package com.jaycode.demo.features.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository repository){
        return  args -> {
         Student jackson =   new Student(
                             "Jackson Stephen",
                             LocalDate.of(1998, 4,5),
                 "jaycode@gmail.com"
                     );

            Student geoffrey =   new Student(
                    "Geoffrey Stephen",
                    LocalDate.of(2002, 2,16),
                    "geoffreystephen@gmail.com"
                        );

            repository.saveAll(
                    List.of(jackson, geoffrey)
            );
        };
    }
}

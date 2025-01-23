package com.jaycode.demo.features.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query("SELECT s FROM Student s WHERE s.email =?1") // => This is what actually is done under the hood
    Optional<Student> findStudentByEmail(String email);
}

package com.jaycode.demo.features.course;

import com.jaycode.demo.features.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private  String name;
    private String code;

    @ManyToMany(mappedBy = "courses")
     // Prevents infinite recursion
    private List<Student> students;


    public Course() {
    }

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Course(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

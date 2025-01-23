package com.jaycode.demo.features.department;

import com.jaycode.demo.features.student.Student;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "department_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private  Long id;
    private String name;

    @OneToMany(mappedBy = "department")
//    @JsonBackReference
//    @JsonManagedReference
    private Set<Student> students = new HashSet<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Long id, String name, Set<Student> student) {
        this.id = id;
        this.name = name;
        this.students = student;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

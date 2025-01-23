package com.jaycode.demo.features.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaycode.demo.features.department.Department;

import java.time.LocalDate;

public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    @JsonInclude(JsonInclude.Include.NON_NULL)  // Only include department if it is not null
    private Department department;

    public StudentDTO(Long id, String name, String email, LocalDate dob, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.department = department;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

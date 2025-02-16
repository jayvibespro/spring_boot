package com.jaycode.demo.features.student;

import com.jaycode.demo.features.course.Course;
import com.jaycode.demo.features.department.Department;
import com.jaycode.demo.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
* @Service marks this to be injected in another class.
* */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getDob(), null))
                .collect(Collectors.toList());
    }


    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }


    public Student addStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());

        if(optionalStudent.isPresent()){
            throw  new ResourceNotFoundException("Email already taken");
        }

       return studentRepository.save(student);
    }

    public boolean deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if(!exists){

           throw new ResourceNotFoundException("Student with id " + studentId + " does not exists");

       }
        studentRepository.deleteById(studentId);
       return  true;
    }

    public Student updateStudent(Long studentId, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with ID " + studentId + " does not exist."
                ));

        if (updatedStudent.getName() != null && !updatedStudent.getName().isEmpty()) {
            existingStudent.setName(updatedStudent.getName());
        }

        if (updatedStudent.getEmail() != null && !updatedStudent.getEmail().isEmpty()) {
            existingStudent.setEmail(updatedStudent.getEmail());
        }

       return studentRepository.save(existingStudent);
    }

    public Student enrollToCourse(Long studentId, Course course) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        if (student.getCourses() == null) {
            student.setCourses(new ArrayList<>());
        }
        student.getCourses().add(course);
       return studentRepository.save(student);
    }

    public Student enrollToDepartment(Long studentId, Department department) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student not found"));

        student.setDepartment(department);
       return studentRepository.save(student);
    }
}

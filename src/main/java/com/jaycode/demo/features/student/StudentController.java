package com.jaycode.demo.features.student;

import com.jaycode.demo.features.course.Course;
import com.jaycode.demo.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private  final StudentService studentService;

    /*
    * @Autowired MAGICALLY creates an instance of StudentService to the constructor.
    * */

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseModel<List<StudentDTO>> getStudents() {
        List<StudentDTO> students = studentService.getStudents();
        return new ResponseModel<>(200, true, "Students fetched successfully", students);
    }

    @GetMapping("{studentId}")
    public ResponseModel<StudentDTO> getStudent(@PathVariable Long studentId) {
        StudentDTO student = studentService.getStudent(studentId);
        return new ResponseModel<>(200, true, "Student fetched successfully", student);
    }


    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return ResponseEntity.ok(new ResponseModel<>(200, true, "Student added successfully", student));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseModel<>(500, false, e.getMessage() + " noma sana", null));
        }
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseModel<Void> deleteStudent(@PathVariable("studentId") Long studentId){
       boolean isDeleted = studentService.deleteStudent(studentId);

       if(!isDeleted){
           return new ResponseModel<>(500,false, "Failed to delete student", null);
       }
        return new ResponseModel<>(200,true, "Student deleted successfully", null);
    }

    @PutMapping(path = "{studentId}")
    public ResponseModel<Student> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody Student student) {
       Student updatedStudent = studentService.updateStudent(studentId, student);
        return new ResponseModel<>(201,true,"Student updated successfully", updatedStudent);
    }

    @PostMapping(path = "/{studentId}/enroll")
    public ResponseModel<Student> enrollToCourse(@PathVariable Long studentId, Course course){
        Student enrolledStudent = studentService.enrollToCourse(studentId, course);

        return new ResponseModel<>(201,true,"Student enrolled to course successfully", enrolledStudent);
    }
}

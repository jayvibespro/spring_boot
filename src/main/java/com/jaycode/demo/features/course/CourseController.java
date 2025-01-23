package com.jaycode.demo.features.course;

import com.jaycode.demo.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseModel<List<Course>> getAllCourses(){
        List<Course> courses =  courseService.getAllCourses();
        return new ResponseModel<>(200, true, "Courses fetched successfully", courses);
    }

    @PostMapping
    public Course addCourse(Course course){
        return  courseService.addCourse(course);
    }
}

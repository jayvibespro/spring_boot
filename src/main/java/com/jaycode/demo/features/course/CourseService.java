package com.jaycode.demo.features.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    List<Course> getAllCourses(){
        return  courseRepository.findAll();
    }

    Course addCourse(Course course){
        return  courseRepository.save(course);
    }
}

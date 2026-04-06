package com.org.java.controller;

import com.org.java.relation.Course;
import com.org.java.relation.Student;
import com.org.java.repository.CourseRepository;
import com.org.java.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

   /* public StudentCourseController(StudentRepository studentRepository,
                                   CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }*/

    @PostMapping("/add")
    public Student saveStudentWithCourse(@RequestBody Student student){
        return   studentRepository.save(student);
    }

    @GetMapping("/findAll")
    public List<Student> findALlStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("findbyId/{studentId}")
    public Student findStudent(@PathVariable Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }
    @GetMapping("/findByName/{name}")
    public List<Student> findStudentsContainingByName(@PathVariable String name){
        return studentRepository.findByNameContaining(name);
    }

    @GetMapping("/search/{price}")
    public List<Course> findCourseLessThanPrice(@PathVariable double price){
        return courseRepository.findByFeeLessThan(price);
    }

}

package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Course;
import com.example.demo.Services.CourseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get a specific course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new course
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }

    // Update course details
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

     @PostMapping("/purchase")
    public ResponseEntity<String> purchaseCourse(@RequestParam String userId, @RequestParam Long courseId) {
        // Assuming you have a method in CourseService that handles purchasing logic
        String response = courseService.purchaseCourse(userId, courseId);
        if ("Course purchased successfully!".equals(response)) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Get courses purchased by a user
    @GetMapping("/user-courses")
    public ResponseEntity<List<Course>> getUserCourses(@RequestParam String userId) {
        List<Course> userCourses = courseService.getUserCourses(userId);
        if (userCourses != null && !userCourses.isEmpty()) {
            return ResponseEntity.ok(userCourses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

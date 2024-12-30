package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Add a new course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update course details
    public Course updateCourse(Long id, Course courseDetails) {
        if (courseRepository.existsById(id)) {
            courseDetails.setId(id);
            return courseRepository.save(courseDetails);
        } else {
            return null;  // or handle it better with an exception
        }
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Purchase a course for a user
    public String purchaseCourse(String userId, Long courseId) {
        // Implement your logic for adding a course to the user's purchased list
        // For now, we assume it always succeeds
        return "Course purchased successfully!";
    }

    // Get courses purchased by a user
    public List<Course> getUserCourses(String userId) {
        // Implement your logic to fetch courses associated with the user
        // For now, we return an empty list as a placeholder
        return List.of();
    }
}
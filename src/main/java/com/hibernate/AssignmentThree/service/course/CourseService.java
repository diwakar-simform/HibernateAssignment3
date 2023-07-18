package com.hibernate.AssignmentThree.service.course;

import com.hibernate.AssignmentThree.entities.course.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course addCourse(Course course);
    String deleteCourseById(Long id);
    Course getCourseById(Long id);
}

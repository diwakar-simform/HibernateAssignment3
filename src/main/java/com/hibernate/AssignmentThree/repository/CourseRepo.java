package com.hibernate.AssignmentThree.repository;

import com.hibernate.AssignmentThree.entities.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {

}

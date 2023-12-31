package com.hibernate.AssignmentThree.service.course;

import com.hibernate.AssignmentThree.entities.course.Course;
import com.hibernate.AssignmentThree.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepo courseRepo;

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        course.getReviews().forEach(review -> review.setCourse(course));
        return courseRepo.save(course);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteCourseById(Long id) {
        String message = "------Wrong Id------";
        if (id != null && id > 0) {
            courseRepo.findById(id).isPresent();
            courseRepo.deleteById(id);
            message = "------Item deleted Successfully-------";
        }
        return message;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        return course.get();
    }


}

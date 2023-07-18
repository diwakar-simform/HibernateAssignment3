package com.hibernate.AssignmentThree.service.review;

import com.hibernate.AssignmentThree.entities.course.Course;
import com.hibernate.AssignmentThree.entities.review.Review;
import com.hibernate.AssignmentThree.repository.CourseRepo;
import com.hibernate.AssignmentThree.repository.ReviewRepo;
import com.hibernate.AssignmentThree.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepo courseRepo;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public String deleteReviewById(Long id) {
        String message = "-----Wrong Id------";
        if (id != null && id > 0){
            reviewRepo.findById(id).isPresent();
            reviewRepo.deleteById(id);
            message = "-------Item deleted Successfully-----";
        }
        return message;
    }

//    Hibernate Assignment 3 : Manage DB transactions using hibernate.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String addReviewWithCourse(Review review, Long id) {
        Optional<Course> course = courseRepo.findById(id);
        reviewRepo.save(review);

        if (course.isPresent()) {
            review.setCourse(course.get());
        } else {
            throw new RuntimeException("-------Transaction Failed-------");
        }

        return "------Review Added Successfully------";
    }

}

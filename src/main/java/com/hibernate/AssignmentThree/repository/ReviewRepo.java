package com.hibernate.AssignmentThree.repository;

import com.hibernate.AssignmentThree.entities.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Long> {

}

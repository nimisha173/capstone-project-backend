package com.ust.review.service;

import com.ust.review.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review addReview(Review review);

    Optional<Review> viewReview(long reviewId);

    List<Review> viewAllReviewForDoctor(long doctorId);

    List<Review> viewAllReviewByUser(long userId);

    Optional<Review> viewByUserIdAndDoctorId(long userId,long doctorId);
    Boolean checkAlreadyExist(long appointmentId);
}

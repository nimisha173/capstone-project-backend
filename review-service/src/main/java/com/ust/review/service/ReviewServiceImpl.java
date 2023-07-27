package com.ust.review.service;

import com.ust.review.domain.Review;
import com.ust.review.exception.ReviewAlreadyExistException;
import com.ust.review.exception.ReviewNotFoundException;
import com.ust.review.repo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Review review) {

        return reviewRepository.save(review);

    }

    @Override
    public Optional<Review> viewReview(long reviewId) throws ReviewNotFoundException {
        var req = reviewRepository.findById(reviewId);
        if (req.isPresent()) {
            return req;
        } else
            throw new ReviewNotFoundException("Review Not Found");
    }


    @Override
    public List<Review> viewAllReviewForDoctor(long doctorId) throws ReviewNotFoundException {
        var req = reviewRepository.findAllReviewsByDoctorId(doctorId);
        if (req.isEmpty()) {
            throw new ReviewNotFoundException("Reviews not found");
        } else {
            return req;
        }
    }

    @Override
    public List<Review> viewAllReviewByUser(long userId) throws ReviewNotFoundException {
        var req = reviewRepository.findReviewsByUserId(userId);
        if (req.isEmpty()) {
            throw new ReviewNotFoundException("Reviews Not found for user");
        } else {
            return req;
        }

    }

    @Override
    public Optional<Review> viewByUserIdAndDoctorId(long userId, long doctorId) throws ReviewNotFoundException {
        var req = reviewRepository.findReviewByUserIdAndDoctorId(userId, doctorId);
        if (req.isEmpty()) {
            throw new ReviewNotFoundException("Reviews Not found for user and Doctor");
        } else
            return req;
    }

    @Override
    public Boolean checkAlreadyExist(long appointmentId) {
        var res = reviewRepository.findReviewsByAppointmentId(appointmentId);
        if (res.isEmpty()) {
            return true;
        } else
            throw new ReviewAlreadyExistException("Review Already Added");
    }
}

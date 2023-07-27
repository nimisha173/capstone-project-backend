package com.ust.review.dto;

import com.ust.review.domain.Review;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {


    public ReviewDto convertToDto(Review review){
        return new ReviewDto(
                review.getReviewId(),
                review.getUserId(),
                review.getDoctorId(),
                review.getAppointmentId(),
                review.getRating(),
                review.getDescription()
        );
    }

    public Review convertToEntity(ReviewDto reviewDto){
        return new Review(
                reviewDto.reviewId(),
                reviewDto.userId(),
                reviewDto.doctorId(),
                reviewDto.appointmentId(),
                reviewDto.rating(),
                reviewDto.description()
        );
    }
}

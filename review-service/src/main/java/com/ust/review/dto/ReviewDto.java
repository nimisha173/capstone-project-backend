package com.ust.review.dto;

public record ReviewDto(
        long reviewId,
        long userId,
        long doctorId,
        long appointmentId,
        int rating,
        String description) {
}

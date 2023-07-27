package com.ust.review.repo;

import com.ust.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
     List<Review> findAllReviewsByDoctorId(long doctorId);
     Optional<Review> findReviewByUserIdAndDoctorId(long userId, long doctorId );
     List<Review> findReviewsByUserId(long userId);
      Optional<Review> findReviewsByAppointmentId(long appointmentId);
}

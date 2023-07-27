package com.ust.review.controller;


import com.ust.review.domain.Review;
import com.ust.review.dto.DocRatingDto;
import com.ust.review.dto.DtoMapper;
import com.ust.review.dto.ReviewDto;
import com.ust.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final DtoMapper dtoMapper;



    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> checkForExisting(@PathVariable int id) {


        return ResponseEntity.status(HttpStatus.OK).body(reviewService.checkAlreadyExist(id));
    }


    @PostMapping("/create")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {


        var request = reviewService.addReview(dtoMapper.convertToEntity(reviewDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.convertToDto(request));
    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> viewReview(@PathVariable long reviewId) {
        var req = reviewService.viewReview(reviewId);
        if (req.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        var response = dtoMapper.convertToDto(req.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ReviewDto>> reviewForDoctor(@PathVariable long doctorId) {
        var response = reviewService.viewAllReviewForDoctor(doctorId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        var res = response.stream().map(review -> dtoMapper.convertToDto(review)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> reviewForUser(@PathVariable long userId) {
        var response = reviewService.viewAllReviewByUser(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
        var res = response.stream().map(review -> dtoMapper.convertToDto(review)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/findreviews/{userId}/{doctorId}")
    public ResponseEntity<ReviewDto> reviewForDoctorByUser(@PathVariable long userId, @PathVariable long doctorId) {
        var response = reviewService.viewByUserIdAndDoctorId(userId, doctorId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        var res = dtoMapper.convertToDto(response.get());
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }


    @GetMapping("/average/{doctorId}")
    public ResponseEntity<DocRatingDto> doctorRatings(@PathVariable long doctorId) {
        var req = reviewService.viewAllReviewForDoctor(doctorId);
        int avRating = (int) req.stream().mapToInt(Review::getRating).average().orElse(0.0);
        DocRatingDto docRatingDto = new DocRatingDto(avRating);
        return ResponseEntity.status(HttpStatus.OK).body(docRatingDto);
    }

}

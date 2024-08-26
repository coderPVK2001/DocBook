package com.docbook.review.controller;

import com.docbook.review.payload.ReviewDto;
import com.docbook.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController()
@RequestMapping("/api/v1/dockbook/review")
public class ReviewController {

    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/doctor/{doctorId}/patient/{patientId}")
    public ResponseEntity<?> AddReviews(@Valid @RequestBody ReviewDto dto, BindingResult result, @PathVariable("doctorId") long doctorId,
                                        @PathVariable("patientId") long patientId){

        if(result.hasErrors()){
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(reviewService.addReview(dto,doctorId,patientId));
    }


}

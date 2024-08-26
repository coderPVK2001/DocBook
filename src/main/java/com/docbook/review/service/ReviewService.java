package com.docbook.review.service;


import com.docbook.review.payload.ReviewDto;


public interface ReviewService {

    ReviewDto addReview(ReviewDto dto,long doctorId,long patientId);
}

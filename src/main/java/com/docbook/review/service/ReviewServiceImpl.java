package com.docbook.review.service;

import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import com.docbook.patient.entity.Patient;
import com.docbook.patient.repository.PatientRepository;
import com.docbook.review.entity.Review;
import com.docbook.review.exception.ReviewAlreadyExistsException;
import com.docbook.review.payload.ReviewDto;
import com.docbook.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.reviewRepository = reviewRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }




    @Override
    public ReviewDto addReview(ReviewDto dto, long doctorId, long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        if(reviewRepository.findByPatientAndDoctor(patient, doctor).isPresent() ){
            throw new ReviewAlreadyExistsException("Review already exists for this patient");
        }
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setPatient(patient);
        review.setDoctor(doctor);
        review.setDescription(dto.getDescription());
        return convertToEntityToDto(reviewRepository.save(review));

    }

    private ReviewDto convertToEntityToDto(Review save) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(save.getId());
        reviewDto.setRating(save.getRating());
        reviewDto.setDescription(save.getDescription());
        reviewDto.setPatientName(save.getPatient().getName());
        reviewDto.setDoctorName(save.getDoctor().getDoctorName());
        return reviewDto;
    }
}

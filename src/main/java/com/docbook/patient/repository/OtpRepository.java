package com.docbook.patient.repository;

import com.docbook.patient.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByMobileAndOtp(String mobile, String otp);
}
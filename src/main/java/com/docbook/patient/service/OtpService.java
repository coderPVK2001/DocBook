package com.docbook.patient.service;

import com.docbook.patient.entity.Otp;
import com.docbook.patient.repository.OtpRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    @Value("${otp.expiration.time.minutes}")
    private int otpExpirationTimeMinutes;

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOtp(String mobile) {
        String otp = String.valueOf(new Random().nextInt(999999));
        Otp otpEntity = new Otp();
        otpEntity.setMobile(mobile);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpRepository.save(otpEntity);

        // Send OTP via SMS
        Message.creator(
                new PhoneNumber(mobile),
                new PhoneNumber(twilioPhoneNumber),
                "Your OTP is: " + otp
        ).create();

        return otp;
    }

    public boolean validateOtp(String mobile, String otp) {

        String mobilee="+"+mobile;
        Optional<Otp> otpEntityOptional = otpRepository.findByMobileAndOtp( mobilee, otp);
        if (otpEntityOptional.isPresent()) {
            Otp otpEntity = otpEntityOptional.get();

            LocalDateTime lastlocalDateTime = otpEntity.getCreatedAt().plusMinutes(otpExpirationTimeMinutes);
            if ( LocalDateTime.now().isBefore(lastlocalDateTime)) {
                return true;
            }
        }
        return false;
    }
}


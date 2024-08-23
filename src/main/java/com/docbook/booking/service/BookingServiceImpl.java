package com.docbook.booking.service;

import com.docbook.booking.entity.Booking;
import com.docbook.booking.payload.BookingDto;
import com.docbook.booking.repository.BookingRepository;
import com.docbook.utilities.PdfService;
import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.exception.ConsultationIdNotFoundException;
import com.docbook.consultation_time.repository.ConsultationTimeRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import com.docbook.patient.entity.Patient;
import com.docbook.patient.exception.PatientIdNotFoundException;
import com.docbook.patient.repository.PatientRepository;
import com.docbook.utilities.TwilioSmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${pdf.filepath.base}")
    private String pdfFilePathBase; // Base path for saving PDFs

    private BookingRepository bookingRepository;
    private ConsultationTimeRepository consultationTimeRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private PdfService pdfService;
    private TwilioSmsService twilioSmsService;

    public BookingServiceImpl(BookingRepository bookingRepository, ConsultationTimeRepository consultationTimeRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, PdfService pdfService, TwilioSmsService twilioSmsService) {
        this.bookingRepository = bookingRepository;
        this.consultationTimeRepository = consultationTimeRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.pdfService = pdfService;
        this.twilioSmsService = twilioSmsService;
    }

    @Override
    public BookingDto addBooking(long patientid, long consultationid) {

        Patient patient = patientRepository.findById(patientid).orElseThrow(() -> new PatientIdNotFoundException("patient id is not found!!"));
        ConsultationTime consultationTime = consultationTimeRepository.findById(consultationid).orElseThrow(() -> new ConsultationIdNotFoundException("consultation id not found!!"));
        Doctor doctor = consultationTime.getDoctor();

        consultationTime.setStatus("booked");
        consultationTimeRepository.save(consultationTime);

        Booking booking = new Booking();
        booking.setPatient(patient);
        booking.setDoctor(doctor);
        booking.setDate(consultationTime.getDate());
        booking.setTime(consultationTime.getTime());
        booking.setBookedOn(LocalDateTime.now());
        booking.setClinicName(doctor.getClinic().getClinicName());
        Booking savedBooking = bookingRepository.save(booking);

        BookingDto bookingDto = entityToDto(booking);

        // Generate and save the PDF
        String savedUrl=null;
        try {
            String filePath = Paths.get(pdfFilePathBase, "booking-" + savedBooking.getId() + ".pdf").toString();
            pdfService.generateAndSavePdf(bookingDto, filePath);

            // Upload the PDF to S3
            String s3BucketName = "docbookbookings";
            String s3Key = "booking-" + savedBooking.getId() + ".pdf"; // Key under which the PDF will be stored in S3

            savedUrl = uploadPdfToS3(filePath, s3BucketName, s3Key);

            //sms sending with twilio
            twilioSmsService
                    .sendSms(bookingDto.getMobile(),"your slot is confirmed \n for more details :-"+savedUrl+"\n don't forget to visit the clinic !!");

        } catch (IOException e) {
            // Handle the exception, maybe log it
            e.printStackTrace();
        }

        bookingDto.setUrl(savedUrl);
        return bookingDto;
    }

    BookingDto entityToDto(Booking booking) {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setPatientName(booking.getPatient().getName());
        bookingDto.setEmail(booking.getPatient().getEmail());
        bookingDto.setMobile(booking.getPatient().getMobile());
        bookingDto.setDate(booking.getDate());
        bookingDto.setTime(booking.getTime());
        bookingDto.setBookedOn(booking.getBookedOn());
        bookingDto.setDoctorName(booking.getDoctor().getDoctorName());
        bookingDto.setClinicName(booking.getClinicName());
        return bookingDto;

    }

    private String uploadPdfToS3(String filePath, String bucketName, String key) {
        S3Client s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretKey)))
                .build();
        File file = new File(filePath);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)               //file name in aws
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));

            // Construct the S3 URL
            return String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);

        } catch (SdkClientException e) {
            // Handle client exceptions
            e.printStackTrace();
            return null;
        }
//The key (path) under which the file will be stored in S3.


    }
}

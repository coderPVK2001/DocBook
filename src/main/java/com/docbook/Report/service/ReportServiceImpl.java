package com.docbook.Report.service;
import com.docbook.Report.entity.Report;
import com.docbook.Report.payload.ReportDto;
import com.docbook.Report.repository.ReportRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import com.docbook.patient.entity.Patient;
import com.docbook.patient.repository.PatientRepository;
import com.docbook.utilities.PdfService;
import com.docbook.utilities.S3Service;
import com.docbook.utilities.TwilioSmsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private TwilioSmsService twilioSmsService;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
  private PdfService pdfService;
   private S3Service s3Service;
    private final ReportRepository reportRepository;

    public ReportServiceImpl(TwilioSmsService twilioSmsService, PatientRepository patientRepository, DoctorRepository doctorRepository, PdfService pdfService, S3Service s3Service,
                             ReportRepository reportRepository) {
        this.twilioSmsService = twilioSmsService;

        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.pdfService = pdfService;

        this.s3Service = s3Service;
        this.reportRepository = reportRepository;
    }


    @Override
    public String generateReport(long doctorId, long patientId, ReportDto dto) throws IOException {
        Patient patient = patientRepository.findById(patientId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        SecureRandom random = new SecureRandom();
        String filePath = Paths.get("C://Users//user//Desktop//report", "report-" + dto.getDisease()+random.nextInt(9999) + "-" + patient.getId() + ".pdf").toString();
        pdfService.createReport(dto, doctor, patient, filePath);
        MultipartFile multipartFile = pdfService.convertPdfToMultipartFile(filePath);
        String url=null;
        try {
            url = s3Service.uploadFile(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Report report = new Report();
        report.setReportName(dto.getDisease()+"Report");
        report.setGeneratedAt(LocalDateTime.now());
        report.setPatient(patient);
        report.setUrl(url);
        reportRepository.save(report);
        twilioSmsService.sendSms(patient.getMobile(),url);
        return "Report is created successfully";

    }

    @Override
    public List<String> getReports(long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        List<Report> allByPatient = reportRepository.findAllByPatient(patient);
        List<String> listOfReports = allByPatient.stream().map(report -> report.getUrl()).collect(Collectors.toList());
        return listOfReports;
    }


}

package com.docbook.Report.controller;

import com.docbook.Report.payload.ReportDto;
import com.docbook.Report.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/docbook/report")
public class ReportController {


    private ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generated/report/patient/{patientId}")
    public ResponseEntity<?> generateReport(@RequestParam long doctorId,@PathVariable long patientId,@RequestBody ReportDto dto) throws IOException {
        return new ResponseEntity<>(reportService.generateReport(doctorId,patientId,dto), HttpStatus.OK);
    }

    @GetMapping("/reports/patient/{patientId}/reports")
    public ResponseEntity<?> getReports(@PathVariable long patientId) {
        return new ResponseEntity<>(reportService.getReports(patientId), HttpStatus.OK);
    }
}

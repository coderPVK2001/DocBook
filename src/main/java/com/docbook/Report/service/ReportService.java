package com.docbook.Report.service;

import com.docbook.Report.entity.Report;
import com.docbook.Report.payload.ReportDto;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    String generateReport(long doctorId, long patientId, ReportDto dto) throws IOException;

    List<String> getReports(long patientId);
}

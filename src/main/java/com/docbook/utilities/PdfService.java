package com.docbook.utilities;
import com.docbook.Report.payload.ReportDto;
import com.docbook.booking.payload.BookingDto;
import com.docbook.doctor.entity.Doctor;
import com.docbook.patient.entity.Patient;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    public void generateAndSavePdf(BookingDto bookingDto, String filePath) throws IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Set up fonts and colors
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLUE);
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            // Add Title
            Paragraph title = new Paragraph("Booking Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Add a table for booking details
            PdfPTable table = new PdfPTable(2); // 2 columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Table headers
            PdfPCell header1 = new PdfPCell(new Phrase("Field", subTitleFont));
            header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header1);

            PdfPCell header2 = new PdfPCell(new Phrase("Details", subTitleFont));
            header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header2);

            // Table data
            table.addCell(new PdfPCell(new Phrase("Patient Name", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getPatientName(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Email", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getEmail(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Mobile", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getMobile(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Slot Date", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getDate().toString(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Slot Time", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getTime().toString(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Booked On", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getBookedOn().toString(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Doctor Name", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getDoctorName(), textFont)));

            table.addCell(new PdfPCell(new Phrase("Clinic Name", textFont)));
            table.addCell(new PdfPCell(new Phrase(bookingDto.getClinicName(), textFont)));

            document.add(table);

            // Closing the document
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }

    public MultipartFile convertPdfToMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);

        // Convert the file to MultipartFile
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                file.getName(),
                "application/pdf",
                input
        );

        input.close();
        return multipartFile;
    }

    public void createReport(ReportDto dto, Doctor doctor, Patient patient, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();

            // Clinic Name Header
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph clinicName = new Paragraph(doctor.getClinic().getClinicName(), titleFont);
            clinicName.setAlignment(Element.ALIGN_CENTER);
            document.add(clinicName);

            // Add some space
            document.add(new Paragraph("\n"));

            // Doctor and Patient Details Table with increased spacing
            float[] columnWidths = {2, 1}; // Adjust the ratio for desired spacing
            PdfPTable detailsTable = new PdfPTable(columnWidths);
            detailsTable.setWidthPercentage(100);

            // Doctor's Details
            PdfPCell doctorCell = new PdfPCell();
            doctorCell.setBorder(PdfPCell.NO_BORDER);
            doctorCell.addElement(new Paragraph("Doctor: " + doctor.getDoctorName()));
            doctorCell.addElement(new Paragraph("Expertise: " + doctor.getSpecialization().getExpertise()));
            doctorCell.addElement(new Paragraph("Mobile: " + doctor.getMobile()));
            detailsTable.addCell(doctorCell);

            // Patient's Details
            PdfPCell patientCell = new PdfPCell();
            patientCell.setBorder(PdfPCell.NO_BORDER);
            patientCell.addElement(new Paragraph("Patient: " + patient.getName()));
            patientCell.addElement(new Paragraph("Mobile: " + patient.getMobile()));
            patientCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Align content to the right
            detailsTable.addCell(patientCell);

            document.add(detailsTable);

            // Add horizontal line
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(new Phrase(new Chunk(new LineSeparator()))));

            // Add space (5 lines)
            document.add(new Paragraph("\n\n"));

            // Disease Title (Bold and Larger Font)
            Font diseaseTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph diseaseTitle = new Paragraph(dto.getDisease()+" Report", diseaseTitleFont);
            diseaseTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(diseaseTitle);

            // Add space before description
            document.add(new Paragraph("\n\n"));

            // Description
            Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            Paragraph description = new Paragraph("Description: " + dto.getDescription(), descriptionFont);
            document.add(description);

            // Add space before the footer
            document.add(new Paragraph("\n\n"));

            // Footer with Doctor's Name and Date (Right-aligned)
            PdfPTable footerTable = new PdfPTable(1);
            footerTable.setWidthPercentage(100);

            document.add(new Paragraph("\n\n\n\n\n\n\n"));

            PdfPCell footerCell = new PdfPCell();
            footerCell.setBorder(PdfPCell.NO_BORDER);
            footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            footerCell.addElement(new Paragraph(  doctor.getDoctorName()));
            footerCell.addElement(new Paragraph("date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            footerTable.addCell(footerCell);

            document.add(footerTable);


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }
}


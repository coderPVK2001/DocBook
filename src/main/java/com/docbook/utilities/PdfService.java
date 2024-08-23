package com.docbook.utilities;
import com.docbook.booking.payload.BookingDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

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
}


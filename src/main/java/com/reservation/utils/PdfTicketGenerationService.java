package com.reservation.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.reservation.entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfTicketGenerationService {

    public byte[] generatePdfTicket(Passenger passenger,String fromLocation,String toLocation,String fromDate,String fromTime,String toTime,String totalDuration) {
        // Use iText to generate PDF
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph("Ticket Information"));
            // Add passenger details to the PDF
            document.add(new Paragraph("Name: " + passenger.getFirstName() + " " + passenger.getLastName()));
            document.add(new Paragraph("Email: " + passenger.getEmail()));
            document.add(new Paragraph("Mobile: " + passenger.getMobile()));
            document.add(new Paragraph("BusId: " + passenger.getBusId()));
            document.add(new Paragraph("RouteId: " + passenger.getRouteId()));
            document.add(new Paragraph("From Location: " + fromLocation));
            document.add(new Paragraph("To Location"  +toLocation));
            document.add(new Paragraph("Date: " + fromDate));
            document.add(new Paragraph("from Time: " + fromTime));
            document.add(new Paragraph("To Time: " + toTime));
            document.add(new Paragraph("Total TravelTime: " + totalDuration));
            // Add more details as needed
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

}

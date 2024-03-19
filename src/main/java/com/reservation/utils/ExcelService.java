package com.reservation.utils;

import com.reservation.entity.Passenger;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public byte[] generateExcel(List<Passenger> passengers) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Passengers");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("Last Name");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Mobile");
        headerRow.createCell(5).setCellValue("Bus ID");
        headerRow.createCell(6).setCellValue("Route ID");

        // Populate data
        int rowNum = 1;
        for (Passenger passenger : passengers) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(passenger.getId());
            dataRow.createCell(1).setCellValue(passenger.getFirstName());
            dataRow.createCell(2).setCellValue(passenger.getLastName());
            dataRow.createCell(3).setCellValue(passenger.getEmail());
            dataRow.createCell(4).setCellValue(passenger.getMobile());
            dataRow.createCell(5).setCellValue(passenger.getBusId());
            dataRow.createCell(6).setCellValue(passenger.getRouteId());
        }

        // Auto-size columns
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}

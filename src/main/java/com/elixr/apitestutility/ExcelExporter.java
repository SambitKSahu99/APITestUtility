/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elixr.apitestutility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author chandrakanth.shaji
 */
public class ExcelExporter {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExporter.class);

    public static void exportDataToExcel(String url, String methodType, Map<String, String> headers,
            Object[][] testCases, String fileName) {
        logger.info("Starting Excel export process");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("API Test Data");
            logger.debug("Header row created");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("URL");
            headerRow.createCell(1).setCellValue("Method Type");
            headerRow.createCell(2).setCellValue("Headers");
            headerRow.createCell(3).setCellValue("Test Name");
            headerRow.createCell(4).setCellValue("Request Body");

            int rowNum = 1; // Start writing from row 1 (row 0 is header)

            for (Object[] testCase : testCases) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(url);
                row.createCell(1).setCellValue(methodType);

                // Writing headers as a single string (or modify to split across rows)
                StringBuilder headersText = new StringBuilder();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    headersText.append(entry.getKey())
                            .append(":")
                            .append(entry.getValue())
                            .append(",");
                }
                // Remove the trailing comma and space if headers are not empty
                if (!headersText.isEmpty()) {
                    headersText.setLength(headersText.length() - 1);
                }
                row.createCell(2).setCellValue(headersText.toString());

                // Writing test data
                row.createCell(3).setCellValue(testCase[0].toString()); // Test Name
                row.createCell(4).setCellValue(testCase[1].toString()); // Request Body
            }
            logger.info("{} test cases written to Excel", testCases.length);

            // Auto-size columns for better visibility
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }
            logger.debug("Columns auto-sized for better visibility");

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }
            logger.info("Excel file created successfully: {}", fileName);
            System.out.println("Excel file created successfully: " + fileName);

        } catch (IOException exception) {
            logger.error("Error occurred while writing Excel file: {}", fileName, exception);
        }
    }
}

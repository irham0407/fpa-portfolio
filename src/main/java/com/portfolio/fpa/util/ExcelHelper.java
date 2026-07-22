package com.portfolio.fpa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class ExcelHelper {

    public static boolean hasExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType)
                || "application/vnd.ms-excel".equals(contentType);
    }

    // Helper untuk mengambil String dari cell
    public static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            default -> "";
        };
    }
}

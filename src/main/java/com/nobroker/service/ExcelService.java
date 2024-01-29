package com.nobroker.service;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository interface for JPA operations

    public void exportUsersToExcel(HttpServletResponse response) throws IOException {
        List<User> userList = userRepository.findAll(); // Fetch all users from the database

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Mobile");
            headerRow.createCell(4).setCellValue("Email Verified");

            // Fill data rows
            int rowNum = 1;
            for (User user : userList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getMobile());
                row.createCell(4).setCellValue(user.isEmailVerified());
            }

            // Set response headers
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

            // Write to response output stream
            workbook.write(response.getOutputStream());
        }
    }
}

package com.nobroker.util;

import com.nobroker.entity.User;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void generatePdf(List<User> userList) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Add logic to create workbook and sheets based on your data
            // ...

            // Save the workbook to a PDF file
            try (FileOutputStream fileOut = new FileOutputStream("F://reports//users.pdf")) {
                workbook.write(fileOut);
                fileOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.nobroker.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class Pdf2Service {

    public byte[] generatePdf(List<User> userList) throws DocumentException {
        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        PdfPTable table = new PdfPTable(6); // Adjust the number of columns based on your User entity
        addTableHeader(table);
        addUserRows(table, userList);

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Mobile");
        table.addCell("Password");
        table.addCell("Email Verified");
    }

    private void addUserRows(PdfPTable table, List<User> userList) {
        for (User user : userList) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getName());
            table.addCell(user.getEmail());
            table.addCell(user.getMobile());
            table.addCell(user.getPassword());
            table.addCell(String.valueOf(user.isEmailVerified()));
        }
    }
}

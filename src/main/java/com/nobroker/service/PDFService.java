package com.nobroker.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PDFService {


    public void generateAndSavePDF(List<User> userList, String filePath) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        for (User user : userList) {
            document.add(new Paragraph("ID: " + user.getId()));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Mobile: " + user.getMobile()));
            document.add(new Paragraph("Email Verified: " + user.isEmailVerified()));
            document.add(new Paragraph("\n"));
        }

        document.close();
    }

    public byte[] readPDFBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

}

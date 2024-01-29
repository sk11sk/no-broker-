package com.nobroker.controller;

import com.itextpdf.text.DocumentException;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.Pdf2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class Pdf2Controller {// this is the get the pdf in table form

    @Autowired
    private Pdf2Service pdfService;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository for accessing User data

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException, DocumentException {
        List<User> userList = userRepository.findAll(); // Fetch your user data from the database

        byte[] pdfBytes = pdfService.generatePdf(userList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "user_data.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

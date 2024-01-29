package com.nobroker.controller;

import com.itextpdf.text.DocumentException;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf-export")
public class PDFController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PDFService pdfService;
// both down load from browser  and save to  locak disk both codes are working
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToPDF() {
        List<User> userList = userRepository.findAll();

        try {
            String filePath = "F://reports//users.pdf";
            pdfService.generateAndSavePDF(userList, filePath);

            byte[] pdfBytes = pdfService.readPDFBytes(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "users.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}

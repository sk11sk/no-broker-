package com.nobroker.controller;


import com.nobroker.service.EmailService;
import com.nobroker.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;


    //http://localhost:8080/api/send-Otp-For-Login?email=sk11sk095r@gmail.com

    @PostMapping("/send-Otp-For-Login")
    public Map<String ,String > sendOtpForLogin(@RequestParam String email){

         return emailVerificationService.sendOtpForUserLogin(email);
    }



    //http://localhost:8080/api/verify-otp-for-login?email=sk11sk095r@gmail.com
    @PostMapping("/verify-otp-for-login")
    public Map<String, String> verifyOtpForLogin(@RequestParam String email, @RequestParam String otp) {
        return emailVerificationService.verifyOtpForUserLogin(email, otp);
    }

}

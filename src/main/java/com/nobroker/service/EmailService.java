package com.nobroker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private UserService userService;


    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public String generateOtp(){    // method is called using this keyword

        return String.format("%06d",new java.util.Random().nextInt(1000000)); // returns back a 6 digit otp

    }

    public Map<String, String> sendOtpEmail(String email){  // key value pair storing like for this email this is the otp

        String otp = generateOtp();   // that otp is stored in this  variable //  calls  public String generateOtp()
        sendEmail(email,"otp for  Email verification", "your otp is :" +otp);//  calls  private void sendEmail(String to , String subject, String text)

        Map<String,String> response =  new HashMap<>();
        response.put("status","success");
        response.put("message","otp sent sucessfully");

        return response;
    }

    private void sendEmail(String to , String subject, String text){  //  sending of email is done

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("youremail@gmail.com");
        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);
       javaMailSender.send(message);


    }
}

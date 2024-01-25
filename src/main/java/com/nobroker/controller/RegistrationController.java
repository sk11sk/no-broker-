package com.nobroker.controller;



import com.nobroker.payload.UserDto;
import com.nobroker.service.EmailService;
import com.nobroker.service.EmailVerificationService;
import com.nobroker.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class RegistrationController {

    private UserService userService;

    private EmailService emailService;

    private EmailVerificationService emailVerificationService;

    public RegistrationController(UserService userService, EmailService emailService, EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
    }

    //http://localhost:8080/api/register // this  url is mandatory to write like this for the better redability of the code and for  searching purpose
    @PostMapping("/register")
    public Map<String,String> createUser(@RequestBody UserDto userDto){
           userService.createUser(userDto);
        Map<String, String> response = emailService.sendOtpForEmailVerification(userDto.getEmail());
        return response;

    }


    //http://localhost:8080/api/verify-otp?email=&otp=
    @PostMapping("/verify-otp")
    public  Map < String,String >  verifyOtp(@RequestParam String  email, @RequestParam String   otp){

       return emailVerificationService.verifyOtpForEmailVerification(email,otp);
    }

}

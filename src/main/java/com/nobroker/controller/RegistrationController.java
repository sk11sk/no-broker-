package com.nobroker.controller;



import com.nobroker.payload.UserDto;
import com.nobroker.service.EmailService;
import com.nobroker.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class RegistrationController {

    private UserService userService;

    private EmailService emailService;

    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    //http://localhost:8080/api/register // this  url is mandatory to write like this for the better redability of the code and for  searching purpose
    @PostMapping("/register")
    public Map<String,String> createUser(@RequestBody UserDto userDto){
           userService.createUser(userDto);
        Map<String, String> response = emailService.sendOtpEmail(userDto.getEmail());
        return response;

    }

}

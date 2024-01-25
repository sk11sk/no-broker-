package com.nobroker.service;


import com.nobroker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    final static Map< String, String > emailOtpMapping = new HashMap<>();  // static import so as to store   otp before sending  the email


    public Map< String, String >  verifyOtpForEmailVerification(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);      // stored otp before sending  the email {emailOtpMapping.get( Object key); }

        Map<String ,String> response = new HashMap<>();   // creating new hashmap to set return a response

        if(storedOtp != null && storedOtp.equals(otp)){    // stored otp and provided otp by the  user matches ???
            User user = userService.getUserByEmail(email);  //  get the user by this provided email from the db
            if(user!= null ){                                // if the user exists by that email this User object cant be null
                userService.verifyEmail(user);            // setting verifyEmail field to true
                emailOtpMapping.remove(email);    // bug fixing (otp should not work for more than  one email verification request )
                response.put("status", "success");
                response.put("message", "Email verified successfully");
            }else{
                response.put("status", "error");
                response.put("message", "User not found");
            }
        }else{
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }
        return response;
    }





    public Map<String, String> sendOtpForUserLogin(String email) {
        if (userService.isEmailVerified(email)) {  ////only verified emails  are eligible for login


            // Send OTP to the user's email
            emailService.sendOtpForLogin(email); // generate otp and send otp to email and also stores  the email and otp in hashmap

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return response;
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email is not verified");
            return response;
        }
    }

    public Map<String, String> verifyOtpForUserLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {
            emailOtpMapping.remove(email);
            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        } else {
            // Invalid OTP
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }

        return response;
    }
}



//1> user registers  --->> provides (name, mobile,email, pasword)[some more fields are there that are associated with the user table in the  db and that is the --Id field is auto generated  and emailVerify is set to false [this boolean field will be true once the email  through otp verification is done ]]
//
//2> email is then sent to the provided email and meanwhile  the user is regsitered(saved in the db)
//
//3> when the otp is sent to the email provided a hashmap stores the  email and otp in the key value pair
//
//4 when we  devlop  the verify Otp  module   user enters its email and the otp user  has got via  email  ,
//       4.1 now we  compare the otp  that was sent to the email{ that is already stored in the has map at the time of sending otp to the mail } and the otp  that is enterd by the user
//       4.2 after verification of otp  it is checked whether the user exists with the particular email id {taken at the starting of otp verification}
//       4.3 now that  the  verification is done  the  field  emailVerify is set to true earlier which was false
//       4.4 and if  verification is not sucessfull then it will  return  the  message of not verified accordingly.
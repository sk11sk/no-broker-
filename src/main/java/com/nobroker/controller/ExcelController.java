package com.nobroker.controller;

import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.ExcelService;
import com.nobroker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private UserService userService; // Assuming you have a UserService

    @Autowired
    private ExcelService excelService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/export")
    public String exportToExcel() {
        try {
            List<User> userList = userRepository.findAll(); // Assuming you have a method to get all users
            excelService.exportUsersToExcel(userList, "F://reports//users.xlsx");
            return "Excel file exported successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error exporting Excel file";
        }
    }
}

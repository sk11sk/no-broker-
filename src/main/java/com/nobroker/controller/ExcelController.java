package com.nobroker.controller;


import com.nobroker.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/users")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/export-excel")
    public void exportUsersToExcel(HttpServletResponse response) throws IOException {
        excelService.exportUsersToExcel(response);
    }
}

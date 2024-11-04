package com.example.secondproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/")
    public String home(Model model) {
        // Fetch employees and departments
        List<Employee> employees = employeeRepository.findAll();
        List<Department> departments = departmentRepository.findAll();

        // Fetch joined employee and department info
        List<EmployeeDTO> employeeDepartmentInfo = employeeRepository.getEmployeeInfo();

        // Set the image URL for each EmployeeDTO
        for (EmployeeDTO info : employeeDepartmentInfo) {
            String imageUrl = s3Service.getImageUrl(info.getImageUrl());
            info.setImageUrl(imageUrl);
            System.out.println("Image URL for " + info.getEmployeeName() + ": " + imageUrl);
        }

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("employeeDepartmentInfo", employeeDepartmentInfo);

        return "home";
    }

    @PostMapping("/s3/upload") // Endpoint to handle file uploads
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            // Handle the case when no file is uploaded
            model.addAttribute("message", "Please select a file to upload.");
            return "home";
        }

        try {

            File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(tempFile);


            String key = file.getOriginalFilename();
            s3Service.uploadImage(key, tempFile.getAbsolutePath());


            tempFile.delete();

            model.addAttribute("message", "File uploaded successfully: " + key);
        } catch (IOException e) {

            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
        }

        return "home";
    }
}

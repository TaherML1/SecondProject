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
    private EmployeeRepository employeeRepository; // Make sure you have this repository

    @Autowired
    private DepartmentRepository departmentRepository; // Autowire DepartmentRepository

    @Autowired
    private S3Service s3Service; // Inject S3Service

    @GetMapping("/")
    public String home(Model model) {
        // Fetch employees and departments
        List<Employee> employees = employeeRepository.findAll(); // Fetch all employees
        List<Department> departments = departmentRepository.findAll(); // Fetch all departments

        // Fetch joined employee and department info
        List<EmployeeDTO> employeeDepartmentInfo = employeeRepository.getEmployeeInfo(); // Fetch employee info with departments

        // Set the image URL for each EmployeeDTO
        for (EmployeeDTO info : employeeDepartmentInfo) {
            String imageUrl = s3Service.getImageUrl(info.getImageUrl());
            info.setImageUrl(imageUrl);
            System.out.println("Image URL for " + info.getEmployeeName() + ": " + imageUrl); // Debug log
        }

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("employeeDepartmentInfo", employeeDepartmentInfo); // Add to model

        return "home"; // Return the name of your HTML file without .html
    }

    @PostMapping("/s3/upload") // Endpoint to handle file uploads
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            // Handle the case when no file is uploaded
            model.addAttribute("message", "Please select a file to upload.");
            return "home"; // Return to the home page with a message
        }

        try {
            // Create a temporary file to store the uploaded file
            File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(tempFile); // Transfer the uploaded file to the temp file

            // Upload the file to S3
            String key = file.getOriginalFilename(); // Use the original filename as the key
            s3Service.uploadImage(key, tempFile.getAbsolutePath()); // Call the upload method

            // Optionally, delete the temp file after upload
            tempFile.delete();

            model.addAttribute("message", "File uploaded successfully: " + key);
        } catch (IOException e) {
            // Handle the exception
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
        }

        return "home"; // Return to the home page
    }
}

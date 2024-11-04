package com.example.secondproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/company")
    public String companyHome(Model model) {
        List<Employee> employees = companyService.getAllEmployees();
        List<Department> departments = companyService.getAllDepartments();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "company"; // Ensure you have a company.html template in the templates folder
    }
}

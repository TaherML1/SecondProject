package com.example.secondproject;

public class EmployeeDTO {
    private String employeeName;
    private String managerName;
    private Double salary;
    private Double commission;
    private String departmentName;
    private String imageUrl;


    public EmployeeDTO(String employeeName, String managerName, Double salary, Double commission, String departmentName, String imageUrl) {
        this.employeeName = employeeName;
        this.managerName = managerName;
        this.salary = salary;
        this.commission = commission;
        this.departmentName = departmentName;
        this.imageUrl = imageUrl;
    }


    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public Double getCommission() { return commission; }
    public void setCommission(Double commission) { this.commission = commission; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

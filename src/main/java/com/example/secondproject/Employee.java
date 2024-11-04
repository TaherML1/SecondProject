package com.example.secondproject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "empno")
    private int empno;

    @Column(name = "ename")
    private String ename;

    @Column(name = "job")
    private String job;

    @Column(name = "mgr")
    private Integer mgr;

    @Column(name = "hiredate")
    private String hiredate;

    @Column(name = "sal")
    private double sal;

    @Column(name = "comm")
    private Double comm;

    @Column(name = "deptno")
    private int deptno;

    @Column(name = "img")
    private String img;
    

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public int getDeptno() {
        return deptno;
    }

}

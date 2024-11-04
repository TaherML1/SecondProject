package com.example.secondproject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "deptno")
    private int deptno;

    @Column(name = "dname")
    private String dname;

    @Column(name = "loc")
    private String loc;

    public int getDeptno(){
        return  deptno;
    }


    public String getDname(){
        return dname;
    }

    public  String getLoc(){
        return  loc;
    }

}
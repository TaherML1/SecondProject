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
    public  void setDeptno(int deptno){
        this.deptno = deptno;
    }

    public String getDname(){
        return dname;
    }
    public void setDname(String dname){
        this.dname = dname;
    }
    public  String getLoc(){
        return  loc;
    }
    public void setLoc(String loc){
        this.loc = loc;
    }
}
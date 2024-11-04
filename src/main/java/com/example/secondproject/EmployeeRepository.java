package com.example.secondproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.example.secondproject.EmployeeDTO(e.ename, m.ename, e.sal, e.comm, d.dname, e.img) " +
            "FROM Employee e " +
            "JOIN Employee m ON e.mgr = m.empno " +
            "JOIN Department d ON e.deptno = d.deptno")
    List<EmployeeDTO> getEmployeeInfo(); // Custom method for fetching joined data
}

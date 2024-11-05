package com.art.mocks.service;


import com.art.mocks.domain.Employee;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

public Employee getEmployeeWithMaxSalary (int departmentID){

}



}

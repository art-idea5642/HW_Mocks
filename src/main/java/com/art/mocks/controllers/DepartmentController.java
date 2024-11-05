package com.art.mocks.controllers;

import com.art.mocks.domain.Employee;
import com.art.mocks.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@RequestParam int departmentID) {
        return departmentService.getEmployeesByDepartment(departmentID);
    }

    @GetMapping("/{id}/salary/sum")
    public double getDepartmentSalarySum(@RequestParam int departmentID) {
        return departmentService.getDepartmentSalarySum(departmentID);
    }

    @GetMapping("/{id}/salary/max")
    public Employee getEmployeeWithMaxSalary(@RequestParam int departmentID) {
        return departmentService.getEmployeeWithMaxSalary(departmentID);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getDepartmentMinSalary(@RequestParam int departmentID) {
        return departmentService.getEmployeeWithMinSalary(departmentID);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }
}


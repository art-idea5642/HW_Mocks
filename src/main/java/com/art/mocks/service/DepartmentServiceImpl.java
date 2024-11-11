package com.art.mocks.service;


import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }


    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .sorted(comparing(Employee::getFirstName).thenComparing(Employee::getSurname))
                .collect(toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().values().stream()
                .sorted(comparing(Employee::getFirstName).thenComparing(Employee::getSurname))
                .collect(groupingBy(Employee::getDepartmentId));
    }

    @Override
    public double getDepartmentSalarySum(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }


}
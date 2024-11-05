package com.art.mocks.service;

import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeAlreadyAddedException;
import com.art.mocks.exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void addEmployee(Employee employee) throws EmployeeAlreadyAddedException, IllegalArgumentException;

    void removeEmployee(String name, String surname) throws EmployeeNotFoundException, IllegalArgumentException;

    Employee findEmployee(String name, String surname) throws EmployeeNotFoundException, IllegalArgumentException;

    Map<String, Employee> getAllEmployees();

    Employee getEmployeeWithMaxSalary(String departmentId) throws EmployeeNotFoundException;

    Employee getEmployeeWithMinSalary(String departmentId) throws EmployeeNotFoundException;

    List<Employee> getAllEmployeesByDepartment(String departmentId);

    Map<String, List<Employee>> getAllEmployeesGroupedByDepartment();
}


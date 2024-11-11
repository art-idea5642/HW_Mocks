package com.art.mocks.service;

import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(int departmentId) throws EmployeeNotFoundException;

    Employee getEmployeeWithMinSalary(int departmentId) throws EmployeeNotFoundException;

    double getDepartmentSalarySum (int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment();

    List<Employee> getEmployeesByDepartment(int departmentId);


}

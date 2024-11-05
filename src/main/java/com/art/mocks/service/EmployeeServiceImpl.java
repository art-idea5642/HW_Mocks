package com.art.mocks.service;

import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeAlreadyAddedException;
import com.art.mocks.exceptions.EmployeeNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();

    private String generateKey(Employee employee) {
        return employee.getFirstName() + " " + employee.getSurname();
    }

    @Override
    public void addEmployee(Employee employee) {
        if (StringUtils.isBlank(employee.getFirstName()) || StringUtils.isBlank(employee.getSurname())) {
            throw new IllegalArgumentException("Имя и фамилия не могут быть пустыми.");
        }

        String formattedFirstName = StringUtils.capitalize(employee.getFirstName().toLowerCase());
        String formattedSurname = StringUtils.capitalize(employee.getSurname().toLowerCase());

        Employee formattedEmployee = new Employee(formattedFirstName, formattedSurname, employee.getSalary(), employee.getDepartmentId());

        String key = generateKey(formattedEmployee);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким ФИО уже существует.");
        }
        employeeMap.put(key, formattedEmployee);
    }


    @Override
    public void removeEmployee(String name, String surname) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(surname)) {
            throw new IllegalArgumentException("Имя и фамилия не могут быть пустыми.");
        }

        name = StringUtils.capitalize(name.toLowerCase());
        surname = StringUtils.capitalize(surname.toLowerCase());

        String key = name + " " + surname;
        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник с таким ФИО не найден.");
        }
        employeeMap.remove(key);
    }

    @Override
    public Employee findEmployee(String name, String surname) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(surname)) {
            throw new IllegalArgumentException("Имя и фамилия не могут быть пустыми.");
        }

        name = StringUtils.capitalize(name.toLowerCase());
        surname = StringUtils.capitalize(surname.toLowerCase());

        String key = name + " " + surname;
        Employee employee = employeeMap.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с таким ФИО не найден.");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employeeMap;
    }

}


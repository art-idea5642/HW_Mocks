package com.art.mocks.controllers;

import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeAlreadyAddedException;
import com.art.mocks.exceptions.EmployeeNotFoundException;
import com.art.mocks.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam int salary,
                              @RequestParam int department) {
        try {
            Employee employee = new Employee(name, surname, salary, department);
            employeeService.addEmployee(employee);
            return "Сотрудник успешно создан";
        } catch (EmployeeAlreadyAddedException e) {
            return "Ошибка: Сотрудник уже существует";
        } catch (IllegalArgumentException e) {
            return "Ошибка: Некорректные данные";
        }
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String name,
                                 @RequestParam String surname) {
        try {
            employeeService.removeEmployee(name, surname);
            return "Сотрудник успешно удален";
        } catch (EmployeeNotFoundException e) {
            return "Ошибка: Сотрудник не найден";
        } catch (IllegalArgumentException e) {
            return "Ошибка: Некорректные данные";
        }
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam String name,
                               @RequestParam String surname) {
        try {
            Employee employee = employeeService.findEmployee(name, surname);
            return "Сотрудник найден: " + employee;
        } catch (EmployeeNotFoundException e) {
            return "Ошибка: Сотрудник не найден";
        } catch (IllegalArgumentException e) {
            return "Ошибка: Некорректные данные";
        }
    }

    @GetMapping("/all")
    public Map<String, Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}



package com.art.mocks.domain;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String surname;
    private final double salary;
    private final int departmentId;

    public Employee(String firstName, String surname, double salary, int departmentId) {
        this.firstName = firstName;
        this.surname = surname;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 &&
                departmentId == employee.departmentId
                && Objects.equals(firstName, employee.firstName) &&
                Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, salary, departmentId);
    }

    @Override
    public String toString() {
        return firstName + " " + surname + " (Salary: " + salary + ", Department: " + departmentId + ")";
    }
}
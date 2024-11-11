package com.art.mocks;

import com.art.mocks.domain.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeTestConstants {

    public static final String FIRST_NAME = "Артём";
    public static final String FIRST_NAME2 = "Елена";
    public static final String SURNAME = "Акопян";
    public static final String SURNAME2 = "Иванова";
    public static final double SALARY = 300;
    public static final double MIN_SALARY = 200;
    public static final int DEPARTMENT_ID = 1;
    public static final int BAD_DEPARTMENT_ID = 2;
    public static final int NON_EXISTENT_DEPARTMENT_ID = 0;

    public static final Employee MAX_SALARY_EMPLOYEE =
            new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
    public static final Employee MIN_SALARY_EMPLOYEE =
            new Employee(FIRST_NAME2, SURNAME, MIN_SALARY, DEPARTMENT_ID);
    public static final Map<String, Employee> EMPLOYEES = new HashMap<>();


    public static final Employee DIFFERENT_DEPARTMENT_EMPLOYEE =
            new Employee(FIRST_NAME2, SURNAME2, SALARY, BAD_DEPARTMENT_ID);
    public static final Map<String, Employee> DIFFERENT_DEPARTMENT_EMPLOYEES =
            new HashMap<>();

    static {
        EMPLOYEES.put(FIRST_NAME + " " + SURNAME, MAX_SALARY_EMPLOYEE);
        EMPLOYEES.put(FIRST_NAME2 + " " + SURNAME2, MIN_SALARY_EMPLOYEE);
    }

    public static final List<Employee> DEPARTMENT_LIST = Collections.unmodifiableList(
            List.of(
                    DIFFERENT_DEPARTMENT_EMPLOYEE
            )
    );

    static {
        DIFFERENT_DEPARTMENT_EMPLOYEES.put(FIRST_NAME + " " + SURNAME, MAX_SALARY_EMPLOYEE);
        DIFFERENT_DEPARTMENT_EMPLOYEES.put(FIRST_NAME2 + " " + SURNAME2, DIFFERENT_DEPARTMENT_EMPLOYEE);
        DIFFERENT_DEPARTMENT_EMPLOYEES.put(FIRST_NAME2 + " " + SURNAME, MIN_SALARY_EMPLOYEE);
    }

    public static final Map<Integer, List<Employee>> DEPARTMENT_MAP =
            DIFFERENT_DEPARTMENT_EMPLOYEES.values().stream().collect(groupingBy(Employee::getDepartmentId));


    public static final double DEPARTMENT1_SALARY_SUM = MIN_SALARY + SALARY;


}

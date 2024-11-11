package com.art.mocks;

import com.art.mocks.domain.Employee;
import com.art.mocks.exceptions.EmployeeAlreadyAddedException;
import com.art.mocks.exceptions.EmployeeNotFoundException;
import com.art.mocks.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.art.mocks.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenTheyDontExist() {

        Employee expected = new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
        assertEquals(0, out.getAllEmployees().size());
        assertFalse(out.getAllEmployees().containsValue(expected));

        out.addEmployee(expected);

        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().containsValue(expected));
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenTheyExist() {
        Employee existingEmployee = new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
        out.addEmployee(existingEmployee);


        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            out.addEmployee(existingEmployee);
        });

        assertEquals(1, out.getAllEmployees().size());
        assertTrue(out.getAllEmployees().containsValue(existingEmployee));
    }


    @Test
    public void shouldRemoveEmployeeWhenTheyExist() {
        Employee employeeToRemove = new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
        out.addEmployee(employeeToRemove);

        assertEquals(1, out.getAllEmployees().size());

        out.removeEmployee(FIRST_NAME, SURNAME);

        assertEquals(0, out.getAllEmployees().size());
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemovingNonExistentEmployee() {
        Employee employeeToRemove = new Employee(FIRST_NAME2, SURNAME2, SALARY, DEPARTMENT_ID);
        out.addEmployee(employeeToRemove);
        assertFalse(out.getAllEmployees().containsKey(FIRST_NAME + " " + SURNAME));

        assertThrows(EmployeeNotFoundException.class, () -> {
            out.removeEmployee(FIRST_NAME, SURNAME);
        });
    }

    @Test
    public void shouldFindEmployeeWhenTheyExist() {
        Employee employeeToFind = new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
        out.addEmployee(employeeToFind);

        Employee foundEmployee = out.findEmployee(FIRST_NAME, SURNAME);

        assertEquals(employeeToFind, foundEmployee);
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindingNonExistentEmployee() {
        Employee employeeToFind = new Employee(FIRST_NAME2, SURNAME2, SALARY, DEPARTMENT_ID);
        out.addEmployee(employeeToFind);

        assertThrows(EmployeeNotFoundException.class, () -> {
            out.findEmployee(FIRST_NAME, SURNAME);
        });
    }

    @Test
    public void shouldReturnAllEmployeesWhenEmployeesExist() {
        Employee employee1 = new Employee(FIRST_NAME, SURNAME, SALARY, DEPARTMENT_ID);
        Employee employee2 = new Employee(FIRST_NAME2, SURNAME2, SALARY, DEPARTMENT_ID); // Вы можете заменить значения константами, если у вас есть такие для "Bob" и "Johnson"

        out.addEmployee(employee1);
        out.addEmployee(employee2);

        Map<String, Employee> allEmployees = out.getAllEmployees();

        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.containsKey(FIRST_NAME + " " + SURNAME));
        assertTrue(allEmployees.containsKey(FIRST_NAME2 + " " + SURNAME2));
    }
}

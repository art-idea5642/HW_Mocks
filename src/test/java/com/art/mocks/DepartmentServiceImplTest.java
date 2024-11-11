package com.art.mocks;

import com.art.mocks.exceptions.EmployeeNotFoundException;
import com.art.mocks.service.DepartmentServiceImpl;
import com.art.mocks.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.art.mocks.EmployeeTestConstants.*;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldGetEmployeeWithMaxSalary() {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertEquals(MAX_SALARY_EMPLOYEE, out.getEmployeeWithMaxSalary(DEPARTMENT_ID));
        assertNotNull(out.getEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldEmployeeNotFoundExceptionIfDepartmentIsEmptyMaxSalary (){
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeWithMaxSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldGetEmployeeWithMinSalary () {
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertEquals(MIN_SALARY_EMPLOYEE, out.getEmployeeWithMinSalary(DEPARTMENT_ID));
        assertNotNull(out.getEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldEmployeeNotFoundExceptionIfDepartmentIsEmptyMinSalary (){
        when(employeeService.getAllEmployees()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeWithMinSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldGetEmployeesByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(DEPARTMENT_LIST, out.getEmployeesByDepartment(BAD_DEPARTMENT_ID));
        assertNotNull(out.getEmployeesByDepartment(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldGetAllEmployeesGroupedByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(DEPARTMENT_MAP, out.getAllEmployeesGroupedByDepartment());
        assertNotNull(out.getAllEmployeesGroupedByDepartment());
    }

    @Test
    public void shouldGetDepartmentSalarySum() {
        when(employeeService.getAllEmployees()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(DEPARTMENT1_SALARY_SUM, out.getDepartmentSalarySum(DEPARTMENT_ID));
        assertEquals(0, out.getDepartmentSalarySum(NON_EXISTENT_DEPARTMENT_ID));
    }
}

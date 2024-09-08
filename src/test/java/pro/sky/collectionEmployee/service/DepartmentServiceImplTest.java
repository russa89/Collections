package pro.sky.collectionEmployee.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collectionEmployee.Employee;

import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextFloat;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Faker faker = new Faker();
    private static final Collection<Employee> employees = List.of(
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextFloat()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextFloat()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextInt(), nextFloat()));


    @Test
    void findWorkerWithMinSalaryByDep() {
    }

    @Test
    void findWorkerWithMaxSalaryByDep() {
    }

    @Test
    void printAllEmployeesByDep() {
    }

    @Test
    void printAllEmployees() {
    }

    @Test
    void findSumOfSalaryByDep() {
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);
        int department = employees.iterator().next().getDepartment();
        double expected = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected += employee.getSalary();
            }
        }
        double actual = departmentService.findSumOfSalaryByDep(department);

        assertThat(actual).isEqualTo(expected);
    }
}

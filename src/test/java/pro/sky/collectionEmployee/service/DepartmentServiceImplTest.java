package pro.sky.collectionEmployee.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collectionEmployee.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static org.apache.commons.lang3.RandomUtils.nextFloat;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void findWorkerWithMinSalaryByDep_WhenCorrectData_ThenReturnCorrectMin() {
        int department = employees.iterator().next().getDepartment();
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);

        Optional<Employee> expected = employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()));

        Optional<Employee> actual = Optional.ofNullable(departmentService.findWorkerWithMinSalaryByDep(department));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findWorkerWithMinSalaryByDep_WhenEmptyMap_ThenThrowAnException() {
        when(employeeService.getEmployees()).thenReturn(new ArrayList<>());
        int department = employees.iterator().next().getDepartment();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> departmentService.findWorkerWithMinSalaryByDep(department));

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void findWorkerWithMaxSalaryByDep_WhenCorrectData_ThenReturnCorrectMax() {
        int department = employees.iterator().next().getDepartment();
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);

        Optional<Employee> expected = employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()));

        Optional<Employee> actual = Optional.ofNullable(departmentService.findWorkerWithMaxSalaryByDep(department));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findWorkerWithMaxSalaryByDep_WhenEmptyMap_ThenThrowAnException() {
        when(employeeService.getEmployees()).thenReturn(new ArrayList<>());
        int department = employees.iterator().next().getDepartment();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> departmentService.findWorkerWithMaxSalaryByDep(department));

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void printAllEmployeesByDep_WhenCorrectDep_TherReturnEmployeeList() {
        int department = employees.iterator().next().getDepartment();
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);
        List<Employee> expected = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected.add(employee);
            }
        }
        List<Employee> actual = departmentService.printAllEmployeesByDep(department);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void printAllEmployeesByDep_WhenMapDoesNotContainDepEmployees_ThenReturnEmptyList() {
        int department = 3;
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);

        List<Employee> actual = departmentService.printAllEmployeesByDep(department);
        assertThat(actual).isEmpty();

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void printAllEmployees_WhenCorrectData_ThenReturnCorrectMap() {
        when(employeeService.getEmployees()).thenReturn((List<Employee>) employees);

        Map<Integer, List<Employee>> expected =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        Map<Integer, List<Employee>> actual = departmentService.printAllEmployees();

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void printAllEmployees_WhenEmptyListOfEmployees_ThenReturnEmptyMap() {
        when(employeeService.getEmployees()).thenReturn(new ArrayList<>());

        Map<Integer, List<Employee>> actual = departmentService.printAllEmployees();

        assertThat(actual).isEmpty();

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void findSumOfSalaryByDep_WhenCorrectData_ThenReturnCorrectSum() {
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

        verify(employeeService, only()).getEmployees();
    }

    @Test
    void findSumOfSalaryByDep_WhenEmptyMap_ThenReturnZero() {
        when(employeeService.getEmployees()).thenReturn(new ArrayList<>());
        int department = employees.iterator().next().getDepartment();

        double actual = departmentService.findSumOfSalaryByDep(department);

        assertThat(actual).isZero();

        verify(employeeService, only()).getEmployees();
    }
}

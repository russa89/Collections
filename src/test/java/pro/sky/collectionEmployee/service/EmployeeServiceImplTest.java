package pro.sky.collectionEmployee.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextFloat;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EmployeeServiceImplTest {

    private static final Faker faker = new Faker();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();


    @Test
    void shouldAddEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int department = nextInt();
        float salary = nextFloat();

        Employee actual = employeeService.addEmployee(firstName, lastName, department, salary);

        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getDepartment()).isEqualTo(department);
        assertThat(actual.getSalary()).isEqualTo(salary);
    }

    @Test
    void shouldAddEmployee_WhenAlreadyAdded_ThenThrowAnException() {

        Employee expected = employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(),
                nextInt(), nextFloat());

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class).isThrownBy(() -> employeeService.addEmployee(expected.getFirstName(), expected.getLastName(),
                expected.getDepartment(), expected.getSalary()));
    }

    @Test
    void findEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        Employee expected = employeeService.addEmployee(firstName, lastName,
                nextInt(), nextFloat());
        Employee actual = employeeService.findEmployee(firstName, lastName);

        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
    }

    @Test
    void findEmployee_WhenNotExists_ThenThrowAnException() {

        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> employeeService.findEmployee
                (faker.name().firstName(), faker.name().lastName()));
    }

    @Test
    void shouldRemoveEmployeeWhenExists() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Employee expected = employeeService.addEmployee(firstName, lastName,
                nextInt(), nextFloat());

        Employee actual = employeeService.removeEmployee(firstName, lastName);

        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
    }

    @Test
    void shouldRemoveEmployee_WhenNotExists_ThenThrowAnException() {

        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> employeeService.removeEmployee
                (faker.name().firstName(), faker.name().lastName()));
    }

    @Test
    void shouldGetEmployees_WhenMapIsEmpty_ThenReturnEmptyCollection() {

        Collection<Employee> actual = employeeService.getEmployees();
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldGetEmployees() {
        List<Employee> expected = new ArrayList<>();
        for (int i = 0; i < nextInt(1, 10); i++) {
            expected.add(employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(),
                    nextInt(), nextFloat()));
        }
        Collection<Employee> actual = employeeService.getEmployees();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void shouldPrintEmployees() {
        List<Employee> expected = new ArrayList<>();
        for (int i = 0; i < nextInt(1, 10); i++) {
            expected.add(employeeService.addEmployee(faker.name().firstName(), faker.name().lastName(),
                    nextInt(), nextFloat()));
        }
        Collection<Employee> actual = employeeService.printEmployees();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}

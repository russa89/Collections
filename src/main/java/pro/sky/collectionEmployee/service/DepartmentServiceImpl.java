package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final List<Employee> employees;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employees = (List<Employee>) employeeService.getEmployees();
    }

    @Override
    public Employee findWorkerWithMinSalaryByDep(int department) {
        final Optional<Employee> employee = employees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()));

        return employee.orElseThrow(() -> new RuntimeException("Employee with max salary in" +
                "department " + department + " is not found"));

    }

    @Override
    public Employee findWorkerWithMaxSalaryByDep(int department) {
        Optional<Employee> employee = employees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()));
        return employee.orElseThrow(() -> new RuntimeException("Employee with min salary in" +
                "department " + department + " is not found"));
    }

    @Override
    public List<Employee> printAllEmployeesByDep(int department) {
        return employees
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());

    }

    @Override
    public List<Employee> printAllEmployees() {
        return employees
                .stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());

    }
}
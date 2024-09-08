package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findWorkerWithMinSalaryByDep(int department) {
        final Optional<Employee> employee = employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()));

        return employee.orElseThrow(() -> new RuntimeException("Employee with min salary in" +
                "department " + department + " is not found"));
    }

    @Override
    public Employee findWorkerWithMaxSalaryByDep(int department) {
        Optional<Employee> employee = employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()));
        return employee.orElseThrow(() -> new RuntimeException("Employee with max salary in" +
                "department " + department + " is not found"));
    }

    @Override
    public List<Employee> printAllEmployeesByDep(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> printAllEmployees() {
        return employeeService.getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Double findSumOfSalaryByDep(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}

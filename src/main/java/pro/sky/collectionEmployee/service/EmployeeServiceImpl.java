package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, float salary) {
        Employee newEmployee = new Employee(firstName, lastName, department, salary);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен ранее");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        employees.stream().findAny().orElseThrow(() -> new EmployeeNotFoundException("Такой сотрудник не найден"));
        return findEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);

        employees.stream().findAny().orElseThrow(() -> new EmployeeNotFoundException("Такой сотрудник не найден"));
        return removeEmployee;
    }
    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableCollection(employees);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
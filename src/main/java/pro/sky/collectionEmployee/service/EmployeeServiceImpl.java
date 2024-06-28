package pro.sky.collectionEmployee.service;


import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;

import java.util.Arrays;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен ранее");
        }

        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException("Хранилище переполнено");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.contains(findEmployee)) {
            return findEmployee;
        } else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        if (employees.contains(findEmployee(firstName, lastName))) {
            return removeEmployee;
        } else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }

    @Override
    public void printAllEmployees() {

        System.out.println(Arrays.toString(employees.toArray()));
    }
}



package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.containsKey(newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен ранее");
        }

        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException("Хранилище переполнено");
        }
        employees.put(Integer.valueOf(newEmployee.getFullName()), newEmployee);
        return newEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(findEmployee.getFullName())) {
            return employees.get(findEmployee.getFullName());
        } else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(removeEmployee.getFullName())) {
            return employees.remove(removeEmployee.getFullName());

        } else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }

    @Override
    public Collection<Employee> getEmployees() {

        return Collections.unmodifiableCollection(employees.values());
    }
}

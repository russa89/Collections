package pro.sky.collectionEmployee.service;


import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Service
public class EmployeeService {

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov"),
            new Employee("Semen", "Semenov"),
            new Employee("Petr", "Petrov"),
            new Employee("Fedor", "Fedorov")
    ));
    int maxEmployees = 7;

    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        try {employees.contains(newEmployee);}
        catch (EmployeeAlreadyAddedException ex){
            System.out.println(ex.getMessage());

        }

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        return newEmployee;
    }
        public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.contains(findEmployee)){
            return findEmployee;
        }
        else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }
    public Employee removeEmployee(String firstName, String lastName)  {
        Employee removeEmployee = new Employee(firstName, lastName);
        if(employees.contains(findEmployee(firstName, lastName))){
            return removeEmployee;
        }
        else throw new EmployeeNotFoundException("Такой сотрудник не найден");

    }
    public void printAllEmployees() {
        System.out.println(employees);
    }

}

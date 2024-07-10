package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;

import java.util.*;

@Service
public interface EmployeeService {
    Map<Integer, Employee> employees = new HashMap<>(Map.of(
            1, new Employee("Ivan", "Ivanov"),
            2, new Employee("Semen", "Semenov"),
            3, new Employee("Petr", "Petrov"),
            4, new Employee("Fedor", "Fedorov")));

    int maxEmployees = 7;


    Employee addEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Collection<Employee> getEmployees();
}

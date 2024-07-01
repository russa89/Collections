package pro.sky.collectionEmployee.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov"),
            new Employee("Semen", "Semenov"),
            new Employee("Petr", "Petrov"),
            new Employee("Fedor", "Fedorov")
    ));
    int maxEmployees = 7;


    Employee addEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    List<Employee> getEmployees();
}

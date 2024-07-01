package pro.sky.collectionEmployee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeNotFoundException;
import pro.sky.collectionEmployee.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionEmployee.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")

public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        try {
            return String.valueOf(employeeServiceImpl.addEmployee(firstName, lastName));
        } catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        } catch (EmployeeStorageIsFullException ex) {
            return "Хранилище переполнено";
        }
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return String.valueOf(employeeServiceImpl.removeEmployee(firstName, lastName));
        } catch (EmployeeNotFoundException e) {
            return "Такой сотрудник не найден";
        }
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        try {
            return String.valueOf(employeeServiceImpl.findEmployee(firstName, lastName));
        } catch (EmployeeNotFoundException e) {
            return "Такой сотрудник не найден";
        }
    }

    @GetMapping("/list")
    public List<Employee> printAllEmployees(){
       return employeeServiceImpl.getEmployees();
    }
}

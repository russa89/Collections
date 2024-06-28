package pro.sky.collectionEmployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")

public class EmployeeController {
    private final EmployeeService employeeService;
    private String firstName;
    private String lastName;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee (@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {

        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {

        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {

        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/list")
    public void getFullListOfEmployees() {;

    }
}

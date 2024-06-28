package pro.sky.collectionEmployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.service.EmployeeServiceImpl;

@RestController
@RequestMapping(path = "/employee")

public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;
    private String firstName;
    private String lastName;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/add")
    public Employee addEmployee (@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {

        return employeeServiceImpl.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {

        return employeeServiceImpl.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {

        return employeeServiceImpl.findEmployee(firstName, lastName);
    }

    @GetMapping("/list")
    public Employee printAllEmployees(){
    return printAllEmployees();

    }
}

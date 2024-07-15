package pro.sky.collectionEmployee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectionEmployee.exceptions.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionEmployee.service.DepartmentService;
import pro.sky.collectionEmployee.service.DepartmentServiceImpl;
import pro.sky.collectionEmployee.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/max-salary")
    public Employee findWorkerWithMaxSalaryByDep(@RequestParam("departmentId") int department) {
        return service.findWorkerWithMaxSalaryByDep(department);
    }

    @GetMapping("/min-salary")
    public Employee findWorkerWithMinSalaryByDep(@RequestParam("departmentId") int department) {
        return service.findWorkerWithMinSalaryByDep(department);
    }

    @GetMapping("/all")
    List<Employee> printAllEmployees(@RequestParam(value = "departmentId", required = false) Integer department) {
        return department != null
                ? service.printAllEmployeesByDep(department)
                : service.printAllEmployees();
    }

}


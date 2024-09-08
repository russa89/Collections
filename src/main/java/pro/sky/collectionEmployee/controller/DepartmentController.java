package pro.sky.collectionEmployee.controller;

import org.springframework.web.bind.annotation.*;

import pro.sky.collectionEmployee.Employee;
import pro.sky.collectionEmployee.service.DepartmentService;
import pro.sky.collectionEmployee.service.DepartmentServiceImpl;
import pro.sky.collectionEmployee.service.EmployeeServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}/salary/max")
    public Employee findWorkerWithMaxSalaryByDep(@PathVariable("department") int department) {
        return service.findWorkerWithMaxSalaryByDep(department);
    }

    @GetMapping("/{id}/salary/min")
    public Employee findWorkerWithMinSalaryByDep(@PathVariable("department") int department) {
        return service.findWorkerWithMinSalaryByDep(department);
    }

    @GetMapping("/{id}/employees")
    List<Employee> printAllEmployeesByDep(@PathVariable("department") int department) {
        return service.printAllEmployeesByDep(department);
    }

    @GetMapping("/employees")
    Map<Integer, List<Employee>> printAllEmployees() {
        return service.printAllEmployees();
    }
//
//    GET http://localhost:8080/department/{id}/salary/sum
//            — возвращает сумму зарплат по департаменту.





}

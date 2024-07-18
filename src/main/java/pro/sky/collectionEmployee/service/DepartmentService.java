package pro.sky.collectionEmployee.service;
import pro.sky.collectionEmployee.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findWorkerWithMinSalaryByDep(int department);

    Employee findWorkerWithMaxSalaryByDep(int department);

    List<Employee> printAllEmployeesByDep(int department);

    Map<Integer, List<Employee>> printAllEmployees();
}

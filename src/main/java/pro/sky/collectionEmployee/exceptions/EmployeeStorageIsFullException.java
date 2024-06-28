package pro.sky.collectionEmployee.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Employee Storage Is Full");
    }
}

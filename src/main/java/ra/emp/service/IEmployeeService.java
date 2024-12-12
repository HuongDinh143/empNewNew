package ra.emp.service;

import ra.emp.model.dto.EmployeeDto;
import ra.emp.model.dto.EmployeeUpdateDto;
import ra.emp.model.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(String empId);
    boolean create(EmployeeDto request);
    boolean update(EmployeeUpdateDto request);
    boolean delete(String empId);
    List<Employee> findByDeptId(int deptId);
    Employee findByEmail(String empEmail);
    Employee findByPhone    (String empPhone);
    List<Employee> findEmployee(String searchValue);
}

package ra.emp.repository;

import ra.emp.model.entity.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findAll();
    Employee findById(String empId);
    boolean save(Employee employee);
    boolean update(Employee employee);
    boolean delete(String empId);
    List<Employee> findByDeptId(int deptId);
    Employee findByEmail(String empEmail);
    Employee findByPhone    (String empPhone);
    List<Employee> findEmployee(String searchValue);


}

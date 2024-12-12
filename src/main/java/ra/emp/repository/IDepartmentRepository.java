package ra.emp.repository;

import ra.emp.model.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    boolean save(Department department);
    boolean update(Department department);
    boolean delete(int deptId);
    Department findById(int deptId);
    Department findByName(String deptName);
}

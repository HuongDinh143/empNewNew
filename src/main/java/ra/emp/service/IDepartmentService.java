package ra.emp.service;

import ra.emp.model.dto.DepartmentDto;
import ra.emp.model.dto.DepartmentDtoUpdate;
import ra.emp.model.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
    boolean create(DepartmentDto request);
    boolean update(DepartmentDtoUpdate request);
    boolean delete(int deptId);
    Department findById(int deptId);
    Department findByName(String deptName);
}

package ra.emp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.emp.model.dto.DepartmentDto;
import ra.emp.model.dto.DepartmentDtoUpdate;
import ra.emp.model.entity.Department;
import ra.emp.repository.IDepartmentRepository;
import ra.emp.service.IDepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public boolean create(DepartmentDto request) {
        Department department = new Department();
        department.setDeptName(request.getDeptName());
        department.setDeptDesc(request.getDeptDesc());
        return departmentRepository.save(department);
    }

    @Override
    public boolean update(DepartmentDtoUpdate request) {
        Department department = departmentRepository.findById(request.getDeptId());
        department.setDeptName(request.getDeptName());
        department.setDeptDesc(request.getDeptDesc());
        return departmentRepository.update(department);
    }
    @Override
    public boolean delete(int deptId) {
        return departmentRepository.delete(deptId);
    }

    @Override
    public Department findById(int deptId) {
        return departmentRepository.findById(deptId);
    }

    @Override
    public Department findByName(String deptName) {
        return departmentRepository.findByName(deptName);
    }
}

package ra.emp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.emp.model.dto.EmployeeDto;
import ra.emp.model.dto.EmployeeUpdateDto;
import ra.emp.model.entity.Department;
import ra.emp.model.entity.Employee;
import ra.emp.repository.IDepartmentRepository;
import ra.emp.repository.IEmployeeRepository;
import ra.emp.service.IEmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImp implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public boolean create(EmployeeDto request) {
        Employee employee = new Employee();
        employee.setEmpId(request.getEmpId());
        employee.setEmpName(request.getEmpName());
        employee.setEmpBirthOfDate(request.getEmpBirthOfDate());
        employee.setEmpSex(request.isEmpSex());
        employee.setEmpAddress(request.getEmpAddress());
        employee.setEmpEmail(request.getEmpEmail());
        employee.setEmpPhone(request.getEmpPhone());
        employee.setEmpAvatar(request.getEmpAvatar());
        Department department = departmentRepository.findById(request.getDeptId());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public boolean update(EmployeeUpdateDto request) {
        Employee employee = employeeRepository.findById(request.getEmpId());
        employee.setEmpName(request.getEmpName());
        employee.setEmpBirthOfDate(request.getEmpBirthOfDate());
        employee.setEmpSex(request.isEmpSex());
        employee.setEmpAddress(request.getEmpAddress());
        employee.setEmpEmail(request.getEmpEmail());
        employee.setEmpPhone(request.getEmpPhone());
        employee.setEmpAvatar(request.getEmpAvatar());
        employee.setEmpStatus(request.isEmpStatus());
        Department department = departmentRepository.findById(request.getDeptId());
        employee.setDepartment(department);
        return employeeRepository.update(employee);
    }

    @Override
    public boolean delete(String empId) {
        return employeeRepository.delete(empId);
    }

    @Override
    public List<Employee> findByDeptId(int deptId) {
        return
                employeeRepository.findByDeptId(deptId);
    }

    @Override
    public Employee findByEmail(String empEmail) {
        return employeeRepository.findByEmail(empEmail);
    }

    @Override
    public Employee findByPhone(String empPhone) {
        return employeeRepository.findByPhone(empPhone);
    }

    @Override
    public List<Employee> findEmployee(String searchValue) {
        return employeeRepository.findEmployee(searchValue);
    }
}

package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.emp.model.entity.Employee;
import ra.emp.repository.IEmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepositoryImp implements IEmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("from Employee where empStatus = :status", Employee.class)
                .setParameter("status", true)
                .getResultList();
    }
    @Override
    public Employee findById(String empId) {
        try {
            return entityManager.createQuery("from Employee WHERE empId=:id", Employee.class)
                    .setParameter("id", empId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean save(Employee employee) {
        try {
            entityManager.persist(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        try {
            entityManager.merge(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String empId) {
        try {
            Employee empDelete = findById(empId);
            empDelete.setEmpStatus(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> findByDeptId(int deptId) {
        try {
            return entityManager.createQuery("from Employee where department.deptId=:deptId", Employee.class)
                    .setParameter("deptId", deptId).getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public Employee findByEmail(String empEmail) {
        try {
            return entityManager.createQuery("from Employee where empEmail=:empEmail", Employee.class)
                    .setParameter("empEmail", empEmail).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Employee findByPhone(String empPhone) {
        try {
            return entityManager.createQuery("from Employee where empPhone=:empPhone", Employee.class)
                    .setParameter("empPhone", empPhone).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployee(String searchValue) {
        if (searchValue == null || searchValue.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return entityManager.createQuery("from Employee where empName like :searchParam " +
                        "or empEmail like :searchParam " +
                        "or empAddress like :searchParam " +
                        "or empPhone like :searchParam", Employee.class)
                .setParameter("searchParam", "%" + searchValue + "%")
                .getResultList();
    }

}

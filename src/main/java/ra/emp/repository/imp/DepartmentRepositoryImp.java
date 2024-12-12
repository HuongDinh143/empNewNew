package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.emp.model.entity.Department;
import ra.emp.repository.IDepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImp implements IDepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("from Department", Department.class).getResultList();
    }

    @Override
    public boolean save(Department department) {
        try {
            entityManager.persist(department);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Department department) {
        try {
            entityManager.merge(department);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int deptId) {
        try {
            Department deptUpdate = findById(deptId);
            entityManager.remove(deptUpdate);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Department findById(int deptId) {
        try {
            return entityManager.find(Department.class, deptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Department findByName(String deptName) {
        try {
            return entityManager.createQuery("from Department where deptName = :name", Department.class)
                    .setParameter("name", deptName).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

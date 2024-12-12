package ra.emp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptId;
    @Column(name = "dept_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String deptName;
    @Column(name = "dep_desc", columnDefinition = "text")
    private String deptDesc;
    @Column(name = "dept_status")
    private boolean deptStatus = true;
    @OneToMany(mappedBy = "department")
    private List<Employee> listEmployees = new ArrayList<>();
}

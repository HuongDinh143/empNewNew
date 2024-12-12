package ra.emp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id", columnDefinition = "char(5)")
    private String empId;
    @Column(name = "emp_name", columnDefinition = "varchar(100)", nullable = false)
    private String empName;
    @Column(name = "emp_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date empBirthOfDate;
    @Column(name = "emp_sex")
    private boolean empSex;
    @Column(name = "emp_address", columnDefinition = "text", nullable = false)
    private String empAddress;
    @Column(name = "emp_email", columnDefinition = "varchar(200)", nullable = false, unique = true)
    private String empEmail;
    @Column(name = "emp_phone", columnDefinition = "varchar(11)", nullable = false, unique = true)
    private String empPhone;
    @Column(name = "avatar", columnDefinition = "text")
    private String empAvatar;
    @Column(name = "emp_status")
    private boolean empStatus = true;
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    Department department = new Department();
}

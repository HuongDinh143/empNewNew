package ra.emp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    @NotBlank
    @Size(max = 100,message = "Tên phòng ban không quá 100 ký tự")
    private String deptName;
    private String deptDesc;
}

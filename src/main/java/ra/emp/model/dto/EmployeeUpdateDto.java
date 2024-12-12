package ra.emp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDto {
    @NotBlank(message = "Mã nhân viên không được bỏ trống")
    @Size(max = 5,message = "Mã nhân viên không quá 5 ký tự")
    private String empId;
    @NotBlank(message = "Tên nhân viên không được bỏ trống")
    @Size(max = 100,message = "Tên nhân viên tối đa 100 ký tự")
    private String empName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date empBirthOfDate;
    private boolean empSex;
    @NotBlank(message = "Địa chỉ không được bỏ trống")
    private String empAddress;
    @NotBlank(message = "email không được bỏ trống")
    @Size(max = 200,message = "email tối đa 200 ký tự")
    @Email(message = "Không đúng định dạng email")
    private String empEmail;
    @NotBlank(message = "Số điện thoại không được bỏ trống")
    @Size(min = 11, max = 11,message = "Số điện thoại có 11 ký tự")
    @Pattern(regexp = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{8}$")
    private String empPhone;
    private String empAvatar;
    @NotNull(message = "Mã phòng ban không được bỏ trống")
    private int deptId;
    private boolean empStatus;
}

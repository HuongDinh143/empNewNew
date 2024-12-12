package ra.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.emp.model.dto.EmployeeDto;
import ra.emp.model.dto.EmployeeUpdateDto;
import ra.emp.model.entity.Department;
import ra.emp.model.entity.Employee;
import ra.emp.service.IDepartmentService;
import ra.emp.service.IEmployeeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employeeController")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/findAll")
    public String findAllEmployees(Model model) {
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("listEmployees", listEmployees);
        return "/employee/list";
    }

    @GetMapping("/initCreate")
    public String initCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        List<Department> listDepartments = departmentService.findAll();

        model.addAttribute("listDepartments", listDepartments);
        return "/employee/create";
    }

    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute("employee") EmployeeDto request,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return "/employee/create";
        }
        Employee isExitingEmail = employeeService.findByEmail(request.getEmpEmail());
        if (isExitingEmail != null && !isExitingEmail.getEmpEmail().equals(request.getEmpEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email đã tồn tại!");
            return "redirect:/employeeController?error=Email đã tồn tại";
        }
        Employee isExitingPhone = employeeService.findByPhone(request.getEmpPhone());
        if (isExitingPhone != null && !isExitingPhone.getEmpPhone().equals(request.getEmpPhone())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Số điện thoại đã tồn tại!");
            return "redirect:/employeeController?error=Số điện thoại đã tồn tại";
        }
        try {
            employeeService.create(request);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm mới thành công");
            return "redirect:/employeeController/findAll";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý!");
            return "redirect:/employeeController/initCreate";
        }
    }

    @GetMapping("/initUpdate")
    public String initUpdate(String empId, Model model) {
        Employee employee = employeeService.findById(empId);
        model.addAttribute("employee", employee);
        List<Department> listDepartments = departmentService.findAll();
        model.addAttribute("listDepartments", listDepartments);
        Department department = departmentService.findById(employee.getDepartment().getDeptId());
        model.addAttribute("department", department);
        return "/employee/update";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("employee") EmployeeUpdateDto request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return "employee/update"; // Trả về view cập nhật để người dùng sửa lỗi
        }

        Employee existingEmail = employeeService.findByEmail(request.getEmpEmail());
        if (existingEmail != null && !existingEmail.getEmpId().equals(request.getEmpId())) {
            model.addAttribute("errorMessage", "Email đã tồn tại, vui lòng chọn email khác.");
            return "employee/update";
        }

        Employee existingPhone = employeeService.findByPhone(request.getEmpPhone());
        if (existingPhone != null && !existingPhone.getEmpId().equals(request.getEmpId())) {
            model.addAttribute("errorMessage", "Số điện thoại đã tồn tại, vui lòng chọn số khác.");
            return "employee/update";
        }

        try {
            // Cập nhật nhân viên
            employeeService.update(request);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin nhân viên thành công.");
            return "redirect:/employeeController/findAll";
        } catch (Exception e) {
            // Log lỗi
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý, vui lòng thử lại.");
            return "redirect:/employeeController/initUpdate?empId=" + request.getEmpId();
        }
    }

    @GetMapping("/delete")
    public String delete(String empId, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.findById(empId);
        if (employee != null) {
            boolean result = employeeService.delete(empId);
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công");
                return "redirect:/employeeController/findAll";
            }
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Xóa nhân viên thất bại");
        return "redirect:/employeeController/findAll";

    }
    @GetMapping("/findEmployee")
    public String findEmployee(@RequestParam(name = "searchValue", required = false) String searchValue, Model model) {
        if (searchValue == null || searchValue.trim().isEmpty()) {
            searchValue = "";
        }

        List<Employee> listEmployees = employeeService.findEmployee(searchValue);
        if (listEmployees.isEmpty()) {
            model.addAttribute("errorMessage", "Không tìm thấy nhân viên phù hợp.");
        } else {
            model.addAttribute("listEmployees", listEmployees);
        }

        return "/employee/list";
    }



}

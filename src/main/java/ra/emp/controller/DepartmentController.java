package ra.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.emp.model.dto.DepartmentDto;
import ra.emp.model.dto.DepartmentDtoUpdate;
import ra.emp.model.entity.Department;
import ra.emp.model.entity.Employee;
import ra.emp.service.IDepartmentService;
import ra.emp.service.IEmployeeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/departmentController")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Department> listDepartments = departmentService.findAll();
        model.addAttribute("listDepartments", listDepartments);
        return "/department/list";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        model.addAttribute("department", new DepartmentDto());
        return "/department/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("department") DepartmentDto request, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return "/department/create";
        }
        Department isExitingName = departmentService.findByName(request.getDeptName());
        if (isExitingName != null && !isExitingName.getDeptName().equals(request.getDeptName())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên phòng ban đã tồn tại!");
            return "redirect:/departmentController?error=Tên phòng ban đã tồn tại";
        }
        try {
            departmentService.create(request);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm mới thành công");
            return "redirect:/departmentController/findAll";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý!");
            return "redirect:/departmentController/initCreate";
        }
    }

    @GetMapping("/initUpdate")
    public String initUpdate(int deptId, Model model) {
        Department deptUpdate = departmentService.findById(deptId);
        model.addAttribute("department", deptUpdate);
        return "/department/update";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("department") DepartmentDtoUpdate request, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return "/department/initUpdate?deptId=" + request.getDeptId();
        }
        Department isExitingName = departmentService.findByName(request.getDeptName());
        if (isExitingName != null && !isExitingName.getDeptName().equals(request.getDeptName())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên phòng ban đã tồn tại!");
            return "redirect:/departmentController?error=Tên phòng ban đã tồn tại";
        }
        try {
            departmentService.update(request);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm mới thành công");
            return "redirect:/departmentController/findAll";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra trong quá trình xử lý!");
            return "redirect:/departmentController/initUpdate?deptId=" + request.getDeptId();
        }
    }
    @GetMapping("/delete")
    public String delete(int deptId , RedirectAttributes redirectAttributes) {
        List<Employee> employees = employeeService.findByDeptId(deptId);
        if (employees.isEmpty()) {
            boolean result = departmentService.delete(deptId);
            if (result) {
                return "redirect:/departmentController/findAll";
            }else {
                return "error";
            }
        }else {
            redirectAttributes.addFlashAttribute("errorMessage","Không thể xóa phòng ban khi có nhân viên");
            return "redirect:/departmentController/findAll";
        }

    }

}

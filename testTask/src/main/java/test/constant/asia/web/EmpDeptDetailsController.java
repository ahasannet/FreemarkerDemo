package test.constant.asia.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.City;
import test.constant.asia.db.Department;
import test.constant.asia.db.Employee;
import test.constant.asia.db.EmployeeDepartment;
import test.constant.asia.db.Office;
import test.constant.asia.editor.DepartmentEditor;
import test.constant.asia.editor.EmployeeEditor;
import test.constant.asia.validation.EmployeeDepartmentValidator;

@Controller
@RequestMapping("/empDeptDetails")
public class EmpDeptDetailsController {
	
	@Autowired AdminDao adminDao;
	private Validator validator;
	private long companyId;
	private long officeId;
	private long employeeId;
	
	
	@InitBinder
   public void initBinder(WebDataBinder dataBinder) {
       dataBinder.registerCustomEditor(Department.class, new DepartmentEditor(adminDao));
       dataBinder.registerCustomEditor(Employee.class, new EmployeeEditor(adminDao));
   }
	
	@RequestMapping(method = RequestMethod.GET)
	public Model onLoad(@RequestParam("companyId")long companyId, @RequestParam("officeId")long officeId, 
			@RequestParam("employeeId")long employeeId, @RequestParam("empDeptId")long empDeptId, Model model) {
		validator = new EmployeeDepartmentValidator(adminDao);
		EmployeeDepartment employeeDepartment = (EmployeeDepartment)adminDao.load(empDeptId, EmployeeDepartment.class);
		if(employeeDepartment == null) {
			employeeDepartment = new EmployeeDepartment();
			employeeDepartment.setEmployee((Employee)adminDao.load(employeeId, Employee.class));
			if(employeeDepartment.getEmployee() == null)
				employeeDepartment.setEmployee(new Employee());
		}
		if(companyId == 0 && employeeDepartment.getEmployee() != null && employeeDepartment.getEmployee().getOffice() != null
				&& employeeDepartment.getEmployee().getOffice().getCompany() != null)
			this.companyId = employeeDepartment.getEmployee().getOffice().getCompany().getId();
		else
			this.companyId = companyId;
		if(officeId == 0 && employeeDepartment.getEmployee() != null && employeeDepartment.getEmployee().getOffice() != null)
			this.officeId = employeeDepartment.getEmployee().getOffice().getId();
		else
			this.officeId = officeId;
		if(employeeId == 0 && employeeDepartment.getEmployee() != null)
			this.employeeId = employeeDepartment.getEmployee().getId();
		else
			this.employeeId = employeeId;
		if(employeeDepartment.getDepartment() == null)
			employeeDepartment.setDepartment(new Department());
		model.addAttribute("companyId", this.companyId);
		model.addAttribute("officeId", this.officeId);
		model.addAttribute("employeeId", this.employeeId);
		model.addAttribute("departmentList", adminDao.getDepartmentList(this.companyId));
		model.addAttribute("employeeList", adminDao.getEmployeeList(companyId, this.officeId));
		model.addAttribute("command", employeeDepartment);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("command") EmployeeDepartment employeeDepartment, BindingResult result) {
		validator.validate(employeeDepartment, result);
		if (result.hasErrors())
			return "empDeptDetails";
		else {
			adminDao.merge(employeeDepartment);	
			return "redirect:/empDeptList.html?companyId=" + employeeDepartment.getDepartment().getCompany().getId() + 
					 "&officeId=" + employeeDepartment.getEmployee().getOffice().getId() + 
					 "&employeeId=" + employeeDepartment.getEmployee().getId();
		}	
	}
	 
}

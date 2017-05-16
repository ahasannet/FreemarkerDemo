package test.constant.asia.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Department;
import test.constant.asia.db.EmployeeDepartment;

public class EmployeeDepartmentValidator implements Validator {

	
	private AdminDao adminDao;
	private List<EmployeeDepartment> empDeptList = null;
	
	
	public EmployeeDepartmentValidator(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
	public boolean supports(Class clazz) {
		return EmployeeDepartment.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		EmployeeDepartment employeeDepartment = (EmployeeDepartment) command;
		if(employeeDepartment.getEmployee() == null || employeeDepartment.getEmployee().getId() == 0)
			errors.rejectValue("employee", "required", "Employee is required");
		if(employeeDepartment.getDepartment() == null || employeeDepartment.getDepartment().getId() == 0)
			errors.rejectValue("department", "required", "Department is required");
		if(employeeDepartment.getEmployee() != null && employeeDepartment.getDepartment() != null) {
			empDeptList = adminDao.getEmployeeDepartmentList(employeeDepartment.getEmployee().getId());
			for(EmployeeDepartment oldEmployeeDepartment : empDeptList) {
				if(employeeDepartment.getId() == oldEmployeeDepartment.getId()) continue;
				if(employeeDepartment.getDepartment().getId() == oldEmployeeDepartment.getDepartment().getId()) {
					errors.rejectValue("department", "required", "Department is already assigned");
					break;
				}
			}
		}
	}

}

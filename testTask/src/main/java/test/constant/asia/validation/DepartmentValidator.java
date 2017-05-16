package test.constant.asia.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import test.constant.asia.dao.AdminDao;
import test.constant.asia.db.Department;

public class DepartmentValidator implements Validator {

	
	private AdminDao adminDao;
	private List<Department> departmentList = null;
	
	
	public DepartmentValidator(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public boolean supports(Class clazz) {
		return Department.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object command, Errors errors) {
		Department department = (Department) command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "Name is required");
		if(department.getCompany() == null || department.getCompany().getId() == 0)
			errors.rejectValue("company", "required", "Company is required");
		if(department.getCompany() != null) {
			departmentList = adminDao.getDepartmentList(department.getCompany().getId());
			for(Department oldDepartment : departmentList) {
				if(department.getId() == oldDepartment.getId()) continue;
				if(department.getName().equalsIgnoreCase(oldDepartment.getName())) {
					errors.rejectValue("name", "required", "'Department Name' exist");
					break;
				}
			}
		}
	}

}
